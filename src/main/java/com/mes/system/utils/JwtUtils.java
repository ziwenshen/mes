package com.mes.system.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * JWT工具类
 * 提供JWT令牌的生成、解析、验证等功能
 */
public class JwtUtils {

    private JwtUtils() {
        // 工具类，防止实例化
    }

    // JWT密钥（实际项目中应从配置文件读取）
    private static final String JWT_SECRET = "mySecretKeyForJWTTokenGenerationAndValidation2024";

    // 默认过期时间（小时）
    private static final int DEFAULT_EXPIRATION_HOURS = 24;

    // 刷新令牌过期时间（天）
    private static final int REFRESH_TOKEN_EXPIRATION_DAYS = 7;

    // 令牌前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // Header名称
    public static final String HEADER_NAME = "Authorization";

    // 密钥
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    // 需要在应用启动时注入
    private static StringRedisTemplate redisTemplate;

    public static void setRedisTemplate(StringRedisTemplate template) {
        redisTemplate = template;
    }

    /**
     * JWT令牌信息类
     */
    public static class TokenInfo {
        private String token;
        private String refreshToken;
        private long expirationTime;
        private long refreshExpirationTime;

        public TokenInfo(String token, String refreshToken, long expirationTime, long refreshExpirationTime) {
            this.token = token;
            this.refreshToken = refreshToken;
            this.expirationTime = expirationTime;
            this.refreshExpirationTime = refreshExpirationTime;
        }

        // Getters
        public String getToken() {
            return token;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public long getExpirationTime() {
            return expirationTime;
        }

        public long getRefreshExpirationTime() {
            return refreshExpirationTime;
        }

    }

    /**
     * 生成JWT令牌
     */
    public static String generateToken(String userId, String username) {
        return generateToken(userId, username, new HashMap<>());
    }

    /**
     * 从请求头获取当前token
     */
    public static String getCurrentToken() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null)
            return null;
        HttpServletRequest request = attrs.getRequest();
        String authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    /**
     * 生成JWT令牌（带额外声明）
     */
    public static String generateToken(String userId, String username, Map<String, Object> claims) {
        return generateToken(userId, username, claims, DEFAULT_EXPIRATION_HOURS);
    }

    /**
     * 生成JWT令牌（指定过期时间）
     */
    public static String generateToken(String userId, String username, Map<String, Object> claims,
            int expirationHours) {
        long currentTime = DateUtils.now();
        long expirationTime = DateUtils.addHours(currentTime, expirationHours);

        // 添加标准声明
        claims.put("userId", userId);
        claims.put("username", username);

        claims.put("iat", currentTime);
        claims.put("exp", expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 生成完整的令牌信息（包含刷新令牌）
     */
    public static TokenInfo generateTokenInfo(String userId, String username) {
        return generateTokenInfo(userId, username, new HashMap<>());
    }

    /**
     * 生成完整的令牌信息（带额外声明）
     */
    public static TokenInfo generateTokenInfo(String userId, String username, Map<String, Object> claims) {
        // 生成访问令牌
        String accessToken = generateToken(userId, username, claims, DEFAULT_EXPIRATION_HOURS);

        // 生成刷新令牌
        Map<String, Object> refreshClaims = new HashMap<>();
        refreshClaims.put("userId", userId);
        refreshClaims.put("username", username);
        refreshClaims.put("type", "refresh");

        String refreshToken = generateRefreshToken(userId, username, refreshClaims);

        long expirationTime = DateUtils.getJwtExpiration(DEFAULT_EXPIRATION_HOURS);
        long refreshExpirationTime = DateUtils.getRefreshTokenExpiration(REFRESH_TOKEN_EXPIRATION_DAYS);

        return new TokenInfo(accessToken, refreshToken, expirationTime, refreshExpirationTime);
    }

    /**
     * 生成刷新令牌
     */
    public static String generateRefreshToken(String userId, String username, Map<String, Object> claims) {
        long currentTime = DateUtils.now();
        long expirationTime = DateUtils.addDays(currentTime, REFRESH_TOKEN_EXPIRATION_DAYS);

        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("type", "refresh");
        claims.put("iat", currentTime);
        claims.put("exp", expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从令牌中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 从令牌中获取用户ID
     */
    public static String getUserIdFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("userId", String.class));
    }

    /**
     * 从令牌中获取过期时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 从令牌中获取签发时间
     */
    public static Date getIssuedAtFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * 从令牌中获取指定声明
     */
    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从令牌中获取所有声明
     */
    public static Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    /**
     * 验证令牌是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 验证令牌是否有效
     */
    public static Boolean validateToken(String token, String username) {
        try {
            final String tokenUsername = getUsernameFromToken(token);
            return (username.equals(tokenUsername) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证令牌是否有效（不检查用户名）
     */
    public static Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     */
    public static String refreshToken(String refreshToken) {
        try {
            if (isTokenExpired(refreshToken)) {
                throw new IllegalArgumentException("Refresh token is expired");
            }

            String userId = getUserIdFromToken(refreshToken);
            String username = getUsernameFromToken(refreshToken);

            // 验证是否为刷新令牌
            Claims claims = getAllClaimsFromToken(refreshToken);
            String tokenType = claims.get("type", String.class);
            if (!"refresh".equals(tokenType)) {
                throw new IllegalArgumentException("Invalid refresh token type");
            }

            return generateToken(userId, username);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to refresh token", e);
        }
    }

    /**
     * 从请求头中解析令牌
     */
    public static String resolveToken(String authHeader) {
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    /**
     * 生成带前缀的令牌字符串
     */
    public static String addTokenPrefix(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return TOKEN_PREFIX + token;
    }

    /**
     * 移除令牌前缀
     */
    public static String removeTokenPrefix(String tokenWithPrefix) {
        return resolveToken(tokenWithPrefix);
    }

    /**
     * 获取令牌剩余有效时间（毫秒）
     */
    public static long getTokenRemainingTime(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            long currentTime = System.currentTimeMillis();
            return expiration.getTime() - currentTime;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 检查令牌是否即将过期（默认30分钟内）
     */
    public static Boolean isTokenNearExpiry(String token) {
        return isTokenNearExpiry(token, 30 * 60 * 1000L); // 30分钟
    }

    /**
     * 检查令牌是否即将过期（指定时间内）
     */
    public static Boolean isTokenNearExpiry(String token, long thresholdMillis) {
        try {
            long remainingTime = getTokenRemainingTime(token);
            return remainingTime > 0 && remainingTime <= thresholdMillis;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 解析令牌获取用户信息
     */
    public static Map<String, Object> parseTokenToUserInfo(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", claims.get("userId"));
            userInfo.put("username", claims.getSubject());
            userInfo.put("issuedAt", claims.getIssuedAt());
            userInfo.put("expiration", claims.getExpiration());
            return userInfo;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse token", e);
        }
    }

    /**
     * 创建匿名令牌（用于测试或临时访问）
     */
    public static String createAnonymousToken() {
        return generateToken("anonymous", "anonymous", new HashMap<>(), 1); // 1小时有效期
    }

    /**
     * 验证是否为匿名令牌
     */
    public static boolean isAnonymousToken(String token) {
        try {
            String username = getUsernameFromToken(token);
            return "anonymous".equals(username);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将token加入黑名单
     */
    public static void invalidateToken(String token) {
        if (token == null || redisTemplate == null)
            return;
        long expire = getTokenRemainingTime(token);
        if (expire > 0) {
            redisTemplate.opsForValue().set("jwt:blacklist:" + token, "1", expire, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 校验token是否在黑名单
     */
    public static boolean isTokenBlacklisted(String token) {
        if (token == null || redisTemplate == null)
            return false;
        return Boolean.TRUE.equals(redisTemplate.hasKey("jwt:blacklist:" + token));
    }
}