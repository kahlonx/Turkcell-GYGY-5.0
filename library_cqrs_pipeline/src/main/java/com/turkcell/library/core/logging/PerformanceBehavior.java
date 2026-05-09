package com.turkcell.library.core.logging;

import com.turkcell.library.core.mediator.cqrs.Request;
import com.turkcell.library.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library.core.mediator.pipeline.RequestHandlerDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2) // Executes after LoggingBehavior (if LoggingBehavior is set to 1)
public class PerformanceBehavior implements PipelineBehavior {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceBehavior.class);
    private static final long THRESHOLD_IN_MILLIS = 3000L;

    @Override
    public <R, T extends Request<R>> R handle(T request, RequestHandlerDelegate<R> next) {
        long startTime = System.currentTimeMillis();

        // Continue to the next behavior or the actual handler
        R response = next.handle();

        long timeTaken = System.currentTimeMillis() - startTime;

        // Check if execution time exceeds the threshold
        if (timeTaken > THRESHOLD_IN_MILLIS) {
            String requestName = request.getClass().getSimpleName();

            // Log as a warning/info alert
            logger.warn("===== PERFORMANCE ALERT =====");
            logger.warn("Long Running Request Detected: {} took {} ms.", requestName, timeTaken);
            logger.warn("Request Data: {}", request.toString());
            logger.warn("=============================");
        }

        return response;
    }
}