package nandari.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//인터페이스는 구현부없는 추상메서드만 가진다. 미리 표준(규칙)을 정해둠. 상속받는 곳에서는 이 메서드를 무조건 오버라이드 해야함.
public interface CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
		throws Throwable;
}