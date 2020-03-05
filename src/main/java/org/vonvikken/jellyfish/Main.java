/*
 * Class:     org.vonvikken.jellyfish.Main
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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(final Stage primaryStage) throws Exception {
    final Parent root = FXMLLoader.load(this.getClass().getResource("/Jellyfish.fxml"));
    primaryStage.setTitle("Jellyfish");
    primaryStage.setScene(new Scene(root, 1600, 900));
    primaryStage.show();
  }

  public static void main(final String[] args) {
    Application.launch(args);
  }
}
