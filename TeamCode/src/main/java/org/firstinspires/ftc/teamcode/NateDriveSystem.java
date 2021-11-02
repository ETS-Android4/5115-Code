package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Drive System Nate", group = "Linear Opmode Nate ")
//@Disabled
public class NateDriveSystem extends LinearOpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor left1 = null;
    private DcMotor right1 = null;
    private DcMotor left2 = null;
    private DcMotor right2 = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        left1 = hardwareMap.get(DcMotor.class, "left1");
        right1 = hardwareMap.get(DcMotor.class, "right1");
        left2 = hardwareMap.get(DcMotor.class, "left2");
        right2 = hardwareMap.get(DcMotor.class, "right2");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        left1.setDirection(DcMotor.Direction.FORWARD);
        right1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.FORWARD);
        right2.setDirection(DcMotor.Direction.REVERSE);

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

            //slowing code
            if (gamepad1.left_bumper) {
                leftSpeed = 0.5;
                rightSpeed = 0.5;
                telemetry.addData("Speed", "Slow Mode");
                telemetry.update();

            } else {
                leftSpeed = 1;
                rightSpeed = 1;
                telemetry.addData("Speed", "Fast");
                telemetry.update();

            }

            // POV Mode uses left stick to go forward, and right stick to turn.
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -leftSpeed, leftSpeed);
            rightPower = Range.clip(drive - turn, -rightSpeed  , rightSpeed);


            // Send calculated power to wheels
            left1.setPower(leftPower);
            right1.setPower(rightPower);
            left2.setPower(leftPower);
            right2.setPower(rightPower);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
