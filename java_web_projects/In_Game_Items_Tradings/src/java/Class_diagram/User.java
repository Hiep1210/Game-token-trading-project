package Class_diagram;

public class User {

	private int id;
	private String username;
	private String password;
	private int game_id;
	private int role_id;
	private double money;

    public User(int id, String username, String password, int game_id, int role_id, double money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.game_id = game_id;
        this.role_id = role_id;
        this.money = money;
    }

	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public int getGame_id() {
		return this.game_id;
	}

	/**
	 * 
	 * @param game_id
	 */
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getRole_id() {
		return this.role_id;
	}

	/**
	 * 
	 * @param role_id
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public double getMoney() {
		return this.money;
	}

	/**
	 * 
	 * @param money
	 */
	public void setMoney(double money) {
		this.money = money;
	}

}