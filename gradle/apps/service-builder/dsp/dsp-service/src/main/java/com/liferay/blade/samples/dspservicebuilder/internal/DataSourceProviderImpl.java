package com.liferay.blade.samples.dspservicebuilder.internal;

import com.liferay.portal.kernel.dao.jdbc.DataSourceFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataSourceProvider;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.Properties;
import javax.sql.DataSource;

public class DataSourceProviderImpl implements DataSourceProvider {

	@Override
	public DataSource getDataSource() {
		try {

            Properties properties = new Properties();

            String jndiName = "jdbc/externalDataSource";
            properties.setProperty("jndi.name", jndiName);

            return DataSourceFactoryUtil.initDataSource(properties);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}