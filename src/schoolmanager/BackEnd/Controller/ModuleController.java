/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static schoolmanager.BackEnd.Controller.LoginController.loginUser;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Level;
import schoolmanager.BackEnd.Model.Module;
import schoolmanager.BackEnd.Model.Template;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.LevelService;
import schoolmanager.BackEnd.Service.ModuleService;
import schoolmanager.BackEnd.uiPresenter.UiLevel;
import schoolmanager.BackEnd.uiPresenter.UiModule;
import static schoolmanager.SchoolManager.alertUpdate;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class ModuleController implements Initializable {

    @FXML
    private TableView<?> moduleTable;
    @FXML
    private TextField name;
    @FXML
    private TableColumn<?, ?> nameC;
    @FXML
    private Label name_err;

    private Module module = new Module();

    private UiModule uiModule = new UiModule();

    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uiModule = new UiModule(name, name_err);
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
            delete.setVisible(false);
        } else {
            update.setVisible(true);
            delete.setVisible(true);
        }
        refrech(moduleTable, nameC, "", new Template());
    }

    public static void refrech(TableView table, TableColumn Column1, String type, Template template) {
        ObservableList<Object> pr;
        switch (type) {
            case "search": {
                pr = (ObservableList<Object>) ModuleService.searchObjectByName(template, "module");
                break;
            }
            default: {
                pr = (ObservableList<Object>) ModuleService.getAllObjects("module");
                break;
            }
        }
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        Module mdl = Mapping.getObjecModuleFromUiModule(uiModule);
        Results.Rstls r = ModuleService.addObject(mdl, "module");
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        }
        refrech(moduleTable, nameC, "", new Template());
    }

    @FXML
    private void update(ActionEvent event) {
        if (module.getId() != 0) {
            Module mdl = Mapping.getObjecModuleFromUiModule(uiModule);
            mdl.setId(module.getId());
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = LevelService.updateObject(mdl, "module");
                if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                    CommunController.alert(r.toString());
                } else {
                    refrech(moduleTable, nameC, "", new Template());
                }
                module = new Module();
                uiModule.clearInputs();
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if (module.getId() != 0) {
            Module mdl = Mapping.getObjecModuleFromUiModule(uiModule);
            mdl.setId(module.getId());
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = LevelService.deleteObject(mdl, "module");
                if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                    CommunController.alert(r.toString());
                } else {
                    refrech(moduleTable, nameC, "", new Template());
                }
                module = new Module();
                uiModule.clearInputs();
            }
        }
    }

    @FXML
    private void select(MouseEvent event) {
        UiModule uiMdl = new UiModule(name, name_err);
        uiMdl.clearInputs();
        Template tmp = (Template) moduleTable.getSelectionModel().getSelectedItem();
        if (tmp != null) {
            module.setName(tmp.getName());
            module.setId(tmp.getId());
            if (module != null) {
                name.setText(module.getName());
            }
        }
    }

    @FXML
    private void search(KeyEvent event) {
        Template g = new Template();
        String s = "";
        if (!name.getText().isEmpty()) {
            g.setName(name.getText());
            s = "name";
            refrech(moduleTable, nameC, "search", g);
        } else {
            refrech(moduleTable, nameC, "", new Template());
        }
    }
}
