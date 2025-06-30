package com.mes.system.service.impl;

import com.mes.system.entity.Property;
import com.mes.system.mapper.PropertyMapper;
import com.mes.system.service.IPropertyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 属性定义表 服务实现类
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements IPropertyService {

}
