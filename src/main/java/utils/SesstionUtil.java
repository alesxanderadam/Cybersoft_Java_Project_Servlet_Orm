package utils;

import javax.servlet.http.HttpServletRequest;

public class SesstionUtil {
    private static SesstionUtil sesstionUtil = null;

    public static SesstionUtil getInstance(){
        if(sesstionUtil == null){
            sesstionUtil = new SesstionUtil();
        }
        return sesstionUtil;
    }

    public void setSesstionUtil(HttpServletRequest req, String key, Object value){
        req.getSession().setAttribute(key, value);
    }

    public Object getSessionUtil(HttpServletRequest req, String key){
        return req.getSession().getAttribute(key);
    }

    public void removeSesstionUtil(HttpServletRequest req, String key){
        req.getSession().removeAttribute(key);
    }
}
