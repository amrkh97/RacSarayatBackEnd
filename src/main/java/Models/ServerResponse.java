package Models;


public class ServerResponse {

	private String responseHexCode;
	private String responseMsg;
	private Integer id;
	private Integer privilege;

	/**
	 * @return the responseHexCode
	 */
	public String getResponseHexCode() {
		return responseHexCode;
	}

	/**
	 * @param responseHexCode the responseHexCode to set
	 */
	public void setResponseHexCode(String responseHexCode) {
		this.responseHexCode = responseHexCode;
	}

	/**
	 * @return the responseMsg
	 */
	public String getResponseMsg() {
		return responseMsg;
	}

	/**
	 * @param responseMsg the responseMsg to set
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
