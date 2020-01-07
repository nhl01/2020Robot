package org.usfirst.frc.team3786.robot.utils;

import java.util.HashMap;

import org.usfirst.frc.team3786.robot.utils.XboxPovButton.POVDirection;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController {

	private static final HashMap<Integer, XboxController> instances = new HashMap<Integer, XboxController>();

	edu.wpi.first.wpilibj.XboxController controller;

	public JoystickButton buttonA;
	public JoystickButton buttonB;
	public JoystickButton buttonX;
	public JoystickButton buttonY;
	public JoystickButton buttonBumperLeft;
	public JoystickButton buttonBumperRight;
	public JoystickButton buttonView;
	public JoystickButton buttonMenu;
	public JoystickButton buttonStickLeft;
	public JoystickButton buttonStickRight;
	public XboxPovButton buttonPovUp;
	public XboxPovButton buttonPovRight;
	public XboxPovButton buttonPovDown;
	public XboxPovButton buttonPovLeft;

	private static double deadzone = 0.13;

	private XboxController(int id) {
		controller = new edu.wpi.first.wpilibj.XboxController(id);
		setupButtons();
		instances.put(id, this);
	}

	/**
	 * Get the instance of XboxController with the given ID
	 * 
	 * @param  id ID of XboxController
	 * 
	 * @return    XboxController
	 */
	public static XboxController getInstance(int id) {
		if (!instances.containsKey(id))
			return new XboxController(id);
		return instances.get(id);
	}

	private void setupButtons() {
		buttonA = new JoystickButton(controller, XboxControllerButton.A.getId());
		buttonB = new JoystickButton(controller, XboxControllerButton.B.getId());
		buttonX = new JoystickButton(controller, XboxControllerButton.X.getId());
		buttonY = new JoystickButton(controller, XboxControllerButton.Y.getId());
		buttonBumperLeft = new JoystickButton(controller, XboxControllerButton.BUMPER_LEFT.getId());
		buttonBumperRight = new JoystickButton(controller, XboxControllerButton.BUMPER_RIGHT.getId());
		buttonView = new JoystickButton(controller, XboxControllerButton.VIEW.getId());
		buttonMenu = new JoystickButton(controller, XboxControllerButton.MENU.getId());
		buttonStickLeft = new JoystickButton(controller, XboxControllerButton.STICK_LEFT.getId());
		buttonStickRight = new JoystickButton(controller, XboxControllerButton.STICK_RIGHT.getId());
		buttonPovUp = new XboxPovButton(controller, POVDirection.UP);
		buttonPovRight = new XboxPovButton(controller, POVDirection.RIGHT);
		buttonPovDown = new XboxPovButton(controller, POVDirection.DOWN);
		buttonPovLeft = new XboxPovButton(controller, POVDirection.LEFT);
	}

	/**
	 * Set global XboxController deadzone
	 * 
	 * @param deadzone Global deadzone
	 */
	public static void setDeadzone(double deadzone) {
		XboxController.deadzone = deadzone;
	}

	/**
	 * Gets the adjusted left stick X value
	 * 
	 * @return Left stick X value
	 */
	public double getLeftStickX() {
		double n = controller.getX(Hand.kLeft);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ? c : -c;
		}
	}

	/**
	 * Gets the adjusted left stick Y value
	 * 
	 * @return Left stick Y value
	 */
	public double getLeftStickY() {
		double n = controller.getY(Hand.kLeft);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ? c : -c;
		}
	}

	/**
	 * Gets the adjusted right stick X value
	 * 
	 * @return Right stick X value
	 */
	public double getRightStickX() {
		double n = controller.getX(Hand.kRight);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ? c : -c;
		}
	}

	/**
	 * Gets the adjusted right stick Y value
	 * 
	 * @return Right stick Y value
	 */
	public double getRightStickY() {
		double n = controller.getY(Hand.kRight);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ? c : -c;
		}
	}

	/**
	 * Gets the adjusted left trigger value
	 * 
	 * @return Left trigger value
	 */
	public double getLeftTrigger() {
		return controller.getTriggerAxis(Hand.kLeft);
	}

	/**
	 * Gets the adjusted right trigger value
	 * 
	 * @return Right trigger value
	 */
	public double getRightTrigger() {
		return controller.getTriggerAxis(Hand.kRight);
	}

	public enum XboxControllerButton {
		A(1), B(2), X(3), Y(4), BUMPER_LEFT(5), BUMPER_RIGHT(6), VIEW(7), MENU(8), STICK_LEFT(9), STICK_RIGHT(10);

		private final int id;

		XboxControllerButton(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

}