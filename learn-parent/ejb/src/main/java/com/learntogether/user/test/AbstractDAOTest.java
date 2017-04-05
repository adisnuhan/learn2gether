package com.learntogether.user.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.h2.jdbc.JdbcConnection;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractDAOTest {

    private static final Log LOG = LogFactory.getLog(AbstractDAOTest.class);
    public static String H2_PERSISTENCE_UNIT = "h2-unit-test";
    public static String MYSQL_PERSISTENCE_UNIT = "mysql-unit-test";
    protected EntityManager em;

    protected EntityTransaction et;
    protected DataSource datasource;
    private EntityManagerFactory createEntityManagerFactory;
    private Connection connection;

	@Before
	public void setUp() throws Exception {
		createEntityManagerFactory = Persistence.createEntityManagerFactory(getPersistenceUnitName());
		this.em = createEntityManagerFactory.createEntityManager();
		this.et = this.em.getTransaction();
		connection = getJDBCConnection(em);
		this.datasource = createDataSource(connection);
		for (String sqlResourceName : getSQLFileResourceNames()) {
			executeSQLScript(sqlResourceName);
		}
	}

	@After
	public void tearDown() throws SQLException {
		this.et.begin();
		Query nativeQuery = em.createNativeQuery("SHUTDOWN IMMEDIATELY");
		nativeQuery.executeUpdate();
		// this.et.commit();
		this.em.close();
		this.createEntityManagerFactory.close();
	}

	protected void executeSQLScript(String sqlResourceName) throws SQLException, IOException {
		try (InputStream sqlInputStream = AbstractDAOTest.class.getResourceAsStream(sqlResourceName)) {
			Reader reader = new InputStreamReader(sqlInputStream, "UTF-8");
			RunScript.execute(connection, reader);
		}
	}

	protected void flushDatabase() {
		em.flush();
		em.clear();
		LOG.info("################################# flushed #############################");
		et.commit();
		et.begin();
	}

	protected abstract String getPersistenceUnitName();

	protected abstract List<String> getSQLFileResourceNames();

	private DataSource createDataSource(final Connection pConnection) {
		return new DataSource() {

			@Override
			public int getLoginTimeout() throws SQLException {
				return 0;
			}

			@Override
			public void setLoginTimeout(final int seconds) throws SQLException {

			}

			@Override
			public PrintWriter getLogWriter() throws SQLException {
				try {
					return new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					LOG.error("Exception: ", e);
				}
				return null;
			}

			@Override
			public void setLogWriter(final PrintWriter out) throws SQLException {

			}

			@Override
			public Connection getConnection(final String username, final String password) throws SQLException {
				return connection;
			}

			@Override
			public Connection getConnection() throws SQLException {
				return connection;
			}

			@Override
			public Logger getParentLogger() throws SQLFeatureNotSupportedException {
				return null;
			}

			@Override
			public <T> T unwrap(Class<T> iface) throws SQLException {
				return null;
			}

			@Override
			public boolean isWrapperFor(Class<?> iface) throws SQLException {
				return false;
			}

		};
	}

	private JdbcConnection getJDBCConnection(EntityManager em2) {
		UnitOfWorkImpl unitOfWork = (UnitOfWorkImpl) ((JpaEntityManager) em.getDelegate()).getActiveSession();
		unitOfWork.beginEarlyTransaction();
		Accessor accessor = unitOfWork.getAccessor();
		accessor.incrementCallCount(unitOfWork.getParent());
		accessor.decrementCallCount();
		final JdbcConnection jdbcConnection = (JdbcConnection) accessor.getConnection();
		return jdbcConnection;
	}

}
