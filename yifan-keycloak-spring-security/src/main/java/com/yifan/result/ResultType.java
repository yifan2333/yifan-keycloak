package com.yifan.result;

/**
 * http 请求返回状态码
 * 
 * @author caowuchao
 * @since 2018年5月16日
 * @version 1.0
 */
public enum ResultType implements BaseResultType {

	OK(200, "成功"),

	FAILURE(63210, "请求失败"),

	BAD_REQUEST(63400, "请求参数错误"),

	UNAUTHORIZED (63401, "非法访问"),

	NOT_PERMISSION (63403, "没有权限"),

	NOT_FOUND(63404, "请求地址错误"),

	METHOD_NOT_ALLOWED(63405, "请求方法错误"),

	UNSUPPORTED_MEDIA_TYPE(63415, "不支持的媒体类型"),

	INTERNAL_ERROR(63500, "服务异常"),
	
	NOT_EXTENDED(63510, "未知错误"),

	FAIL_LIST(63520, "上传失败");


	private Integer code;
	private String msg;

	ResultType(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer code() {
		return code;
	}

	@Override
	public String msg() {
		return msg;
	}

	public static ResultType getResultTypeByCode(int code) {
		for (ResultType obj : ResultType.values()) {
			if (obj.code() == code) {
				return obj;
			}
		}
		return ResultType.NOT_EXTENDED;
	}
}
