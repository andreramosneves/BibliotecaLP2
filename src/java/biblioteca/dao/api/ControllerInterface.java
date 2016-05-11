
package biblioteca.dao.api;

import javax.servlet.http.HttpServletRequest;


public interface ControllerInterface {
    enum ReturnType {REDIRECT, FORWARD}
    public void init(HttpServletRequest request);
    public void execute();
    public String getReturnPage();
    public ReturnType getReturnType();
}
