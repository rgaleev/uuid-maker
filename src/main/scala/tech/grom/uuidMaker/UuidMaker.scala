package tech.grom.uuidMaker

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.input.{Clipboard, ClipboardContent}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint._

object UuidMaker extends JFXApp {

  private val errorLabel = new Label {
    textFill = Color.Red
  }
  private val label = new Label {
  }
  private val textField = new TextField {
    text.onChange {
      label.text = makeUuid(text.value)
    }
  }
  private val systemClipboard = Clipboard.systemClipboard
  private val clipboardContent = new ClipboardContent()
  stage = new PrimaryStage {
    title = "Uuid maker"

    scene = new Scene {
      fill = Color.rgb(38, 38, 38)
      content = new VBox {
        padding = Insets(20, 30, 20, 30)
        children = Seq(
          new HBox {
            padding = Insets(10, 20, 10, 20)
            children = Seq(
              errorLabel
            )
          },
          new HBox {
            padding = Insets(10, 20, 10, 20)
            children = Seq(
              textField,
              new Button {
                text = "Transform"
                onAction = handle {
                  label.text = makeUuid(textField.text.value)
                }
              }
            )
          },
          new HBox {
            padding = Insets(10, 20, 10, 20)
            children = Seq(
              label
            )
          },
          new HBox {
            padding = Insets(10, 20, 10, 20)
            children = Seq(
              new Button {
                text = "Copy to clipboard"
                onAction = handle {
                  clipboardContent.putString(label.text.value)
                  systemClipboard.content_=(clipboardContent)
                  textField.clear()
                }
              }
            )
          }
        )
      }
    }

  }

  def makeUuid(s: String): String = {
//    "bdfd07c4-122b-4cdc-9eba-00f45c232e18"
//    "bdfd07c4-122d-4cdc-9eba-00f45c232e18"
//    "bdfd07c4122b4cdc9eba00f45c232e18"
    if (s.length == 0) {
      errorLabel.text = ""
      ""
    } else  if (s.length < 32) {
      errorLabel.text = "Too short string. Can't create UUID"
      ""
    } else if (s.length > 32) {
      errorLabel.text = "Too long string."
      ""
    } else {
      errorLabel.text = ""
      s.substring(0, 8) + "-" + s.substring(8, 12) + "-" + s.substring(12, 16) +
        "-" + s.substring(16, 20) + "-" + s.substring(20, 32)
    }
  }
}