package edu.team935.MyPlugin;

import edu.wpi.first.shuffleboard.api.data.MapData;
import edu.wpi.first.shuffleboard.api.data.types.MapType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import java.util.LinkedList;

@Description(dataTypes = { MapType.class }, name = "My Map Bound Widget")
@ParametrizedController(value = "MyMapBoundWidget.fxml")
public class MyMapBoundWidget extends SimpleAnnotatedWidget<MapData> implements ChangeListener<MapData> {

	@FXML
	private AnchorPane _thePane;

	@FXML
	private Rectangle _target;

	private final SimpleStringProperty _targetXKey = new SimpleStringProperty(this, "targetX", "");
	private final SimpleStringProperty _targetYKey = new SimpleStringProperty(this, "targetY", "");
	private final SimpleStringProperty _targetWKey = new SimpleStringProperty(this, "targetW", "");
	private final SimpleStringProperty _targetHKey = new SimpleStringProperty(this, "targetH", "");
		
	public MyMapBoundWidget ()
	{
		// Export the properties to set the key value to bind to each gauge.
		
		// Set up a listener that gets triggered every time the map is updated.
		dataProperty().addListener(this);
	}

	public String getTargetXKey ()
	{
		return _targetXKey.getValue();
	}
	
	public void setTargetXKey (String targetX)
	{
		_targetXKey.setValue(targetX);
	}
	
	public String getTargetYKey ()
	{
		return _targetYKey.getValue();
	}
	
	public void setTargetYKey (String targetY)
	{
		_targetYKey.setValue(targetY);
	}
	
	public String getTargetWKey ()
	{
		return _targetWKey.getValue();
	}
	
	public void setTargetWKey (String targetW)
	{
		_targetWKey.setValue(targetW);
	}
	
	public String getTargetHKey ()
	{
		return _targetHKey.getValue();
	}
	
	public void setTargetHKey (String targetH)
	{
		_targetHKey.setValue(targetH);
	}
	
	
	@Override
	public java.util.List<edu.wpi.first.shuffleboard.api.prefs.Group> getSettings() {

		LinkedList<Group> propertyList = new LinkedList<Group>();

		propertyList.add(Group.of("Map Key Values"
		, Setting.of("Target X Key", "The key value in the map to assign to the target X.", _targetXKey, String.class)
		, Setting.of("Target Y Key", "The key value in the map to assign to the target Y.", _targetYKey, String.class)
		, Setting.of("Target W Key", "The key value in the map to assign to the target W.", _targetWKey, String.class)
		, Setting.of("Target H Key", "The key value in the map to assign to the target H.", _targetHKey, String.class)
		));

		return propertyList;
	  }


	@Override
	public Pane getView() {

		return _thePane;
	}

	
	/**
	 * Sets the value of the specified gauge control with some validation to make sure the gauge control
	 * is assigned to a value in the map.
	 * 
	 * @param g The gauge control to set the value in.
	 * @param key The key value from the map to find the data to set in the gauge.
	 */
//	private void setValue (Gauge g, String key)
//	{
//		if (key != null && !key.isEmpty() && dataProperty().get().get(key) != null)
//			g.valueProperty().set((double) dataProperty().get().get(key));
//	}
	
	@Override
	public void changed(ObservableValue<? extends MapData> arg0, MapData arg1, MapData arg2) {
		
		// Invoked when the map changes.  It gets the value from the map by the key value,
		// then updates the appropriate parameter of the rectangle.

		if (_targetXKey.getValue() != null && !_targetXKey.getValue().isEmpty() && dataProperty().get().get(_targetXKey.getValue()) != null)
			_target.setX((double) dataProperty().get().get(_targetXKey.getValue()));

		if (_targetYKey.getValue() != null && !_targetYKey.getValue().isEmpty() && dataProperty().get().get(_targetYKey.getValue()) != null)
			_target.setY((double) dataProperty().get().get(_targetYKey.getValue()));

		if (_targetWKey.getValue() != null && !_targetWKey.getValue().isEmpty() && dataProperty().get().get(_targetWKey.getValue()) != null)
			_target.setWidth((double) dataProperty().get().get(_targetWKey.getValue()));

		if (_targetHKey.getValue() != null && !_targetHKey.getValue().isEmpty() && dataProperty().get().get(_targetHKey.getValue()) != null)
			_target.setHeight((double) dataProperty().get().get(_targetHKey.getValue()));

//		_target.setX(Double.parseDouble(_targetXKey.getValue()));
//		_target.setY(Double.parseDouble(_targetYKey.getValue()));
//		_target.setWidth(Double.parseDouble(_targetWKey.getValue()));
//		_target.setHeight(Double.parseDouble(_targetHKey.getValue()));
	}

}
