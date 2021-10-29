package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Drive System", group = "Linear Opmode")
//@Disabled
public class DriveSystem extends LinearOpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor left = null;
    private DcMotor right = null;
    private DcMotor spinner = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        spinner = hardwareMap.get(DcMotor.class, "spinner");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);
        spinner.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            // Set the speed as a variable to reduce space usage
            double leftSpeed = 1;
            double rightSpeed = 1;
            double spinnerSpeed = 0;

            //space where slowing code will go
            if (gamepad1.left_bumper) {
                leftSpeed = 0.5;
                rightSpeed = 0.5;
                telemetry.addData("Speed", "Slow");
            } else if (gamepad1.a) {
                spinnerSpeed = 1;
                telemetry.addData("Spinner", "On");
            } else {
                leftSpeed = 1;
                rightSpeed = 1;
                spinnerSpeed = 0;
                telemetry.addData("Speed", "Fast");
            }
            telemetry.update();

            // POV Mode uses left stick to go forward, and right stick to turn.
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -leftSpeed, leftSpeed);
            rightPower = Range.clip(drive - turn, -rightSpeed  , rightSpeed);


            // Send calculated power to wheels
            left.setPower(leftPower);
            right.setPower(rightPower);
            spinner.setPower(spinnerSpeed);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}