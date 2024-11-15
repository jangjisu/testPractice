package sample.cafekisok.spring.config;


public class SecondInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SecondInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("SecondInterceptor preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("SecondInterceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("SecondInterceptor afterCompletion");
    }
}

