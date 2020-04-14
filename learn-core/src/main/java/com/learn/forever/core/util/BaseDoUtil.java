package com.learn.forever.core.util;

import com.bluewhale.base.mybatis.BaseDO;
import com.bluewhale.constant.BaseConstants;

import java.util.Date;

/**
 * @author : huangziwang
 * @version 1.0
 * @projectName：category-common
 * @className：BaseDoUtil
 * @date 2019年4月19日 下午3:30:54
 * @E-mail:huangziwang@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Deprecated
public class BaseDoUtil {

    public static void insert(BaseDO baseDO) {
        baseDO.setCreateBy(baseDO.getCreateBy());
        baseDO.setGmtCreate(new Date());
        baseDO.setGrdDelete(BaseConstants.GRD_DELETE_0);
        baseDO.setGmtModify(new Date());
    }

    public static void update(BaseDO baseDO) {
        baseDO.setCreateBy(null);
        baseDO.setModifyBy(baseDO.getModifyBy());
        baseDO.setGmtModify(new Date());
    }

}
