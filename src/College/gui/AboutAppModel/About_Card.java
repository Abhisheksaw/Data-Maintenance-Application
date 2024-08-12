/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.gui.AboutAppModel;

import javax.swing.Icon;

/**
 *
 * @author hp
 */
public class About_Card {
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public About_Card(Icon icon, String title, String description) {
        this.icon = icon;
        this.title = title;
        this.description = description;
    }

    public About_Card() {
    }

    private Icon icon;
    private String title;
    private String description;
}
