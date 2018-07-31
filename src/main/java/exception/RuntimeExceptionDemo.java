package exception;

/**
 * 运行时错误
 * @author xuxb
 */
class ITaxException extends RuntimeException {

	/**
	 * 一个是默认的1L，比如：private static final long serialVersionUID = 1L;
	 * 一个是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段，比如： private static final long
	 * serialVersionUID = xxxxL;
	 * 当你一个类实现了Serializable接口，如果没有显示的定义serialVersionUID， Eclipse会提供这个 提示功能告诉你去定义
	 * 。在Eclipse中点击类中warning的图标一下，Eclipse就会 自动给定两种生成的方式。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 错误编码
	 */
	private String errorCode;
	
	/** 异常信息
	 * @param message
	 */
	public ITaxException(String message) {
		super(message);
	}
	
	/** 异常信息
	 * @param errorCode
	 * @param message
	 */
	public ITaxException(String errorCode, String message) {
		this(message);
		this.errorCode = errorCode;
	}
	
	/** 异常信息
	 * @param errorCode
	 * @param message
	 * @param cause 根异常类（可以存入任何异常）
	 */
	public ITaxException(String errorCode, String message, Throwable cause) {
		this(message, cause);
		this.errorCode = errorCode;
	}
	/**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     * @param cause
     *            根异常类（可以存入任何异常）
     */
    public ITaxException(String message, Throwable cause) {
        super(message, cause);
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

public class RuntimeExceptionDemo {
	
	public static void main(String[] args) {
		int i = 9;
		if (i / 3 > 1) {
			throw new ITaxException("I Am code", "lukaer", new ITaxException("3443"));
		}
		
	}

}
