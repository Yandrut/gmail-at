package org.yandrut.gmail_at.utils;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;

public class CucumberEventListener implements EventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

    private void handleTestStepFinished(TestStepFinished event) {
        if (event.getResult().getStatus() == Status.FAILED) {
            LoggingUtils.attachScreenShot();
        }
    }
}
