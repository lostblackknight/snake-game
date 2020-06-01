package team.tit.gluttonoussnake.domain;

/**
 * 用来封装数据对象
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class ResultInfo {
	
    private boolean flag;					//返回结果正常为true，异常返回false
    private Object data;					//返回结果数据对象
    private String msg;						//返回消息
    private int mark;
    
    public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public ResultInfo() {
    }
    
	public ResultInfo(Object data) {
		super();
		this.data = data;
	}

	public ResultInfo(boolean flag, Object data) {
		super();
		this.flag = flag;
		this.data = data;
	}

	public ResultInfo(boolean flag, String msg) {
		this.flag = flag;
		this.msg = msg;
	}

	public ResultInfo(boolean flag, Object data, String msg) {
		this.flag = flag;
		this.data = data;
		this.msg = msg;
	}

	public ResultInfo(boolean flag, Object data, String msg, int mark) {
		this.flag = flag;
		this.data = data;
		this.msg = msg;
		this.mark = mark;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
