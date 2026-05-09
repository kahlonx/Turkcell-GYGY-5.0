package com.turkcell.library.core.logging;

import com.turkcell.library.core.mediator.cqrs.Request;
import com.turkcell.library.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library.core.mediator.pipeline.RequestHandlerDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Component
@Order(1)
public class LoggingBehavior implements PipelineBehavior {

    private static final Logger logger = LoggerFactory.getLogger(LoggingBehavior.class);

    @Override
    public <R, T extends Request<R>> R handle(T request, RequestHandlerDelegate<R> next) {
        String requestName = request.getClass().getSimpleName();

        // 1. Log Request
        logger.info("===== Pipeline: Handling Request =====");
        logger.info("Request Name: {}", requestName);
        logger.info("Request Data: {}", request.toString());

        long startTime = System.currentTimeMillis();

        // 2. Execute next step in pipeline (or the final handler)
        R response = next.handle();

        long timeTaken = System.currentTimeMillis() - startTime;

        // 3. Log Response
        logger.info("===== Pipeline: Handled Request =====");
        logger.info("Request Name: {} (Completed in {}ms)", requestName, timeTaken);

        if (response != null) {
            logger.info("Response Data: {}", response.toString());
        } else {
            logger.info("Response Data: [Void/Null]");
        }

        return response;
    }
}