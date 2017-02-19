package com.example.silence.reviews;

import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Silence on 09-Nov-16.
 */
public class SelectSingleToggleButtonLayout implements CompoundButton.OnCheckedChangeListener {

    private ArrayList<ToggleButton> toggleButtons = new ArrayList<>();
    private ToggleChangedListener toggleChangedListener;


    public SelectSingleToggleButtonLayout(ArrayList<ToggleButton> toggleButtons) {
        if (toggleButtons != null)
            for (ToggleButton toggleButton : toggleButtons) {
                addToggleButton(toggleButton);
            }
    }

    public SelectSingleToggleButtonLayout() {
    }

    public void addToggleButton(ToggleButton toggleButton) {
        toggleButton.setOnCheckedChangeListener(this);
        toggleButtons.add(toggleButton);
    }

    public void addToggleButton(ToggleButton... toggleButtons) {
        for (ToggleButton togglebutton : toggleButtons) {
            addToggleButton(togglebutton);
        }
    }

    public void selectToggleButton(int index) {
        if (index != -1)
            toggleButtons.get(index).setChecked(true);
    }

    public void selectToggleButton(String i) {
        int index = -1;
        try {
            index = Integer.parseInt(i);
        } catch (Exception e) {
        }
        if (index != -1)
            toggleButtons.get(index).setChecked(true);
    }

    public void setOnToggleChangedListener(ToggleChangedListener toggleChangedListener) {
        this.toggleChangedListener = toggleChangedListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton toggleButton, boolean b) {
        if (b) {
            for (ToggleButton tb : toggleButtons) {
                tb.setChecked(false);
            }
            for (ToggleButton tb : toggleButtons) {
                if (tb.getId() == toggleButton.getId() && tb.isChecked() != true) {
                    tb.setChecked(true);
                    if (toggleChangedListener != null)
                        toggleChangedListener.onToggleChanged(tb);
                }
            }

        }

    }

    public ToggleButton getSelectedToggleButton() {
        for (ToggleButton toggleButton : toggleButtons) {
            if (toggleButton.isChecked()) {
                return toggleButton;
            }
        }
        return null;
    }

    public void clearSelection() {
        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.setChecked(false);
        }
    }

    public interface ToggleChangedListener {
        void onToggleChanged(ToggleButton toggleButton);
    }
}
