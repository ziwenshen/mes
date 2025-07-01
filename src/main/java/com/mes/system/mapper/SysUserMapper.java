package com.mes.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mes.system.entity.SysUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
