/*
 *
 * Copyright (C) 2014 Jiri Fajta
 * Made public in 2026
 * License: MIT license
 *
 */

package org.subdivisionsimulation.components;

public class Toggle {

    private int current_index;// holds currently selected option [1..N]
    private int number_of_options = 1;// holds total options.
    private String[] label_index = null;// holds Lables for each option

    /**
     * <p>Toggle function to switch between values in a given range. Features rotation option after option N-1 goes back to 0 and other way round</p>
     * @param numberOfOptions set number of options without labels. 
     */
    public Toggle(int numberOfOptions) {
        this.number_of_options = numberOfOptions;
        this.label_index = new String[numberOfOptions];
        this.current_index = 0;
    }
    
    /**
     * <p>Toggle function to switch between values in a given range. Features rotation option after option N-1 goes back to 0 and other way round</p>
     * @param labelIndex set a list with provided labels. 
     */
    public Toggle(String[] labelIndex) {
        if(labelIndex == null){
            return;
        }
        this.label_index = labelIndex;
        this.number_of_options = labelIndex.length;
        this.current_index = 0;
    }

    /**
     * <p>Toggle clockwise, rotate to next option. After option N-1 goes back to 0.</p>
     */
    public void toggleClockwise() {
        if (current_index < number_of_options - 1) {
            current_index++;
        } else {
            current_index = 0;
        }
    }

    /**
     * <p>Toggle counter clockwise, rotate to previous option. After option 0 goes back to N-1.</p>
     */
    public void toggleCounterClockwise() {
        if (current_index > 0) {
            current_index--;
        } else {
            current_index = number_of_options - 1;
        }
    }
    
    /**
     * <p>Get selected option as index.</p>
     * @return Get selected option as index. 
     */
    public int getCurrentIndex() {
        return current_index;
    }
    
    /**
     * <p>Get selected option as label.</p>
     * @return Get selected option as label. 
     */
    public String getCurrentLable() {
        if (label_index != null) {
            return label_index[current_index ];
        } else {
            return "";
        }
    }
    
    /**
     * <p>Get all available labels.</p>
     * @return Get all available labels in an array.
     */
    public String[] getAllLableIndex() {
        return label_index;
    }
    
    /**
     * <p>Set option that needs to be active by index.</p>
     * @param index of the option that needs to be active.
     * @return True if succeed else false.
     */
    public boolean setCurrentIndex(int index) {
        if (index > -1 && index < number_of_options) {
            current_index = index;
             return true;
        }else{
            return false;// failed to set index
        }
    }
    
    /**
     * <p>Set option that needs to be active by label name.</p>
     * @param name of the option that needs to be active.
     * @return True if succeed else false.
     */
    public boolean setCurrentValue(String name) {
        for (int i = 0; i < label_index.length; i++) {
            if (label_index[i].equals(name)) {
                setCurrentIndex(i);
                return true;
            }
        }
        return false;// failed to set name
    }
}
