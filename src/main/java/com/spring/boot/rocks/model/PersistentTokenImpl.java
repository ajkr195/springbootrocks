package com.spring.boot.rocks.model;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("persistentTokenRepository")
@Transactional
public class PersistentTokenImpl implements PersistentTokenRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		PersistentLogins logins = new PersistentLogins();
		logins.setUsername(token.getUsername());
		logins.setSeries(token.getSeries());
		logins.setToken(token.getTokenValue());
		logins.setLastUsed(token.getDate());
		sessionFactory.getCurrentSession().save(logins);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		PersistentLogins logins = sessionFactory.getCurrentSession().get(PersistentLogins.class, seriesId);

		if (logins != null) {
			return new PersistentRememberMeToken(logins.getUsername(), logins.getSeries(), logins.getToken(),
					logins.getLastUsed());
		}

		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		sessionFactory.getCurrentSession().createQuery("delete from PersistentLogins" + " where username=:userName")
				.setParameter("userName", username).executeUpdate();
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		Session session = sessionFactory.getCurrentSession();
		PersistentLogins logins = session.get(PersistentLogins.class, series);
		logins.setToken(tokenValue);
		logins.setLastUsed(lastUsed);
	}

}
