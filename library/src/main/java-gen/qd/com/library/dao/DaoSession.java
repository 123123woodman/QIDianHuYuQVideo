package qd.com.library.dao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

import qd.com.library.bean.LogInfoBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig logInfoBeanDaoConfig;

    private final LogInfoBeanDao logInfoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        logInfoBeanDaoConfig = daoConfigMap.get(LogInfoBeanDao.class).clone();
        logInfoBeanDaoConfig.initIdentityScope(type);

        logInfoBeanDao = new LogInfoBeanDao(logInfoBeanDaoConfig, this);

        registerDao(LogInfoBean.class, logInfoBeanDao);
    }
    
    public void clear() {
        logInfoBeanDaoConfig.clearIdentityScope();
    }

    public LogInfoBeanDao getLogInfoBeanDao() {
        return logInfoBeanDao;
    }


}
