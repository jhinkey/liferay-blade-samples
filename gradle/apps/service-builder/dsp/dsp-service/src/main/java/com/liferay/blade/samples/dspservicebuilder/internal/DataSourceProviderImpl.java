package com.liferay.blade.samples.dspservicebuilder.internal;

import com.liferay.portal.kernel.dao.jdbc.DataSourceFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataSourceProvider;
import com.liferay.portal.kernel.jndi.JNDIUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DataSourceProviderImpl implements DataSourceProvider {

    @Override
	public DataSource getDataSource() {
		Thread currentThread = Thread.currentThread();
		ClassLoader classLoader = currentThread.getContextClassLoader();
		currentThread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());
		try {
			Properties jndiEnvironmentProperties = PropsUtil.getProperties(
				PropsKeys.JNDI_ENVIRONMENT, true);
			Context context = new InitialContext(jndiEnvironmentProperties);
			return (DataSource)JNDIUtil.lookup(
				context, "jdbc/externalDataSource");
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		finally {
			currentThread.setContextClassLoader(classLoader);
		}
	}

}