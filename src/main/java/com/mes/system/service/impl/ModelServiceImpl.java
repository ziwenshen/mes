package com.mes.system.service.impl;

import com.mes.system.entity.Model;
import com.mes.system.mapper.ModelMapper;
import com.mes.system.service.IModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务模型定义表 服务实现类
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {

}
