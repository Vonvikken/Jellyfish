/*
 * Class:     org.vonvikken.jellyfish.JellyfishController
 * Author:    Vincenzo Stornanti
 *
 * Copyright 2020 Vincenzo Stornanti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vonvikken.jellyfish;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JellyfishController {

  private static final Logger LOGGER = LoggerFactory.getLogger(JellyfishController.class);
  private static final double INITIAL_RADIUS = 100.0;
  private static final double MAX_OFFSET = 30.0;

  private final DoubleProperty xRadius = new SimpleDoubleProperty(INITIAL_RADIUS);
  private final DoubleProperty yRadius = new SimpleDoubleProperty(INITIAL_RADIUS);
  private final DoubleProperty xOffset = new SimpleDoubleProperty();
  private final DoubleProperty yOffset = new SimpleDoubleProperty();
  private final DoubleProperty overshoot = new SimpleDoubleProperty(0.03);
  private final DoubleProperty duration = new SimpleDoubleProperty(5.0);
  private final IntegerProperty pulsation = new SimpleIntegerProperty(40);
  private final Timeline timeline = new Timeline();
  private final Random random = new Random();

  @FXML private Pane container;

  @FXML private Ellipse circle;

  @FXML
  private void initialize() {

    this.circle.radiusXProperty().bind(this.xRadius);
    this.circle.radiusYProperty().bind(this.yRadius);
    this.circle
        .centerXProperty()
        .bind(this.xOffset.add(this.container.widthProperty().divide(2.0)));
    this.circle
        .centerYProperty()
        .bind(this.yOffset.add(this.container.heightProperty().divide(2.0)));

    this.timeline.setCycleCount(Animation.INDEFINITE);
    this.timeline.setAutoReverse(true);

    this.animate();
    this.timeline.play();
  }

  private void animate() {

    this.timeline.stop();

    final Interpolator interpolatorX =
        new Interpolator() {
          @Override
          protected double curve(final double t) {
            return Math.sin(t)
                * Math.sin(JellyfishController.this.pulsation.get() * t + Math.PI / 2.0);
          }
        };

    final Interpolator interpolatorY =
        new Interpolator() {
          @Override
          protected double curve(final double t) {
            return Math.sin(t) * Math.sin(JellyfishController.this.pulsation.get() * t);
          }
        };

    final var finalRadius = INITIAL_RADIUS * (1 + this.overshoot.get());

    final var oX = MAX_OFFSET * (2 * this.random.nextDouble() - 1.0);
    final var oY = MAX_OFFSET * (2 * this.random.nextDouble() - 1.0);
    final var kvXR = new KeyValue(this.xRadius, finalRadius, interpolatorX);
    final var kvYR = new KeyValue(this.yRadius, finalRadius, interpolatorY);
    final var kvXO = new KeyValue(this.xOffset, oX);
    final var kvYO = new KeyValue(this.yOffset, oY);

    final var kf = new KeyFrame(Duration.seconds(this.duration.get() / 2), kvXR, kvYR, kvXO, kvYO);

    this.timeline.getKeyFrames().setAll(kf);
    this.timeline.setOnFinished(e -> this.animate());
    LOGGER.debug(
        "\nR:\t{}\noX:\t{}\noY:\t{}\nX:\t{}\nY:\t{}",
        finalRadius,
        oX,
        oY,
        this.xOffset.get(),
        this.yOffset.get());
    this.timeline.play();
  }
}
