package testproject.threadlocal;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtils {

	@Autowired
	@Qualifier("first")
protected SessionFactory factory;

ThreadLocal<Session> threadlocal = new ThreadLocal<Session>();

public HibernateUtils() {
System.out.println("HibernateUtlis intilize time");
}

public Session OpenSession() {
Session session = null;
if (threadlocal.get() == null) {
session = factory.openSession();
threadlocal.set(session);
} else {
session = threadlocal.get();
if (!session.isOpen()) {
threadlocal.remove();
session = factory.openSession();
threadlocal.set(session);
}
}

return session;

}

public void CloseConnection() {
if (threadlocal.get() != null) {
threadlocal.get().close();
threadlocal.remove();
}
}
  public Session getCurrentSession()
  {
  Session session = factory.getCurrentSession();
return session;
  }

}
