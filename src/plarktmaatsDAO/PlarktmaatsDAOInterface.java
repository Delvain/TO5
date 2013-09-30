package plarktmaatsDAO;

public interface PlarktmaatsDAOInterface<T> {
	public void create(T t);
	public T read(String pk);
	public void update(String pk, T t);
	public void delete(String pk);
}
