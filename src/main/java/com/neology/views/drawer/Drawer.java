/*
 * Copyright (C) 2017 zsel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.neology.views.drawer;

import com.neology.controllers.DrawerController;
import com.neology.views.AbstractView;
import com.neology.views.ViewFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author obsidiam
 */
public class Drawer extends AbstractView{
    private Parent contentPane;
    private FXMLLoader fxml = new FXMLLoader();

    public Drawer(String layout){
        fxml.setLocation(this.getClass().getResource(layout));
    }
    
    public Pane getDrawerContent(){
        return (Pane)this.contentPane;
    }
    
    @Override
    public DrawerController getController(){
        return fxml.getController();
    }

    @Override
    public String getLayout() {
        return fxml.getLocation().toString();
    }

    @Override
    public ViewFactory getFactory() {
        return ViewFactory.getInstance();
    }

    @Override
    public void loadView() {
        try {
            this.contentPane = fxml.load();
            this.setPrefWidth(((Pane)((Pane)(contentPane)).getChildren().get(0)).getPrefWidth());
            AnchorPane.setTopAnchor(contentPane, 0d);
            AnchorPane.setBottomAnchor(contentPane, 0d);
            AnchorPane.setRightAnchor(contentPane, 0d);
            this.getChildren().add(contentPane);
        } catch (IOException ex) {
            Logger.getLogger(Drawer.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
