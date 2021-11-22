package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Encoder Position", group = "Linear Opmode")

public class GetEncodersPos extends LinearOpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor spinner = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive = hardwareMap.get(DcMotor.class, "left");
        rightDrive = hardwareMap.get(DcMotor.class, "right");
        spinner = hardwareMap.get(DcMotor.class, "spinner");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        spinner.setDirection(DcMotor.Direction.FORWARD);

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




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

            String status;

            //space where slowing code will go
            if (gamepad1.left_bumper && gamepad1.a) {
                spinner.setDirection(DcMotor.Direction.FORWARD);
                leftSpeed = 0.5;
                rightSpeed = 0.5;
                spinnerSpeed = 0.5;
                status = "spinner on; slow";
            } if (gamepad1.left_bumper && gamepad1.b) {
                spinner.setDirection(DcMotor.Direction.REVERSE);
                leftSpeed = 0.5;
                rightSpeed = 0.5;
                spinnerSpeed = 0.5;
                status = "spinner on; backwards & slow";
            } else if (gamepad1.left_bumper) {
                leftSpeed = 0.5;
                rightSpeed = 0.5;
                status = "slow";
            } else if (gamepad1.a) {
                spinner.setDirection(DcMotor.Direction.FORWARD);
                spinnerSpeed = 0.5;
                status = "spinner on";
            } else if (gamepad1.b) {
                spinner.setDirection(DcMotor.Direction.REVERSE);
                spinnerSpeed = 0.5;
                status = "spinner on; backwards";
            } else {
                leftSpeed = 1;
                rightSpeed = 1;
                spinnerSpeed = 0;
                spinner.setDirection(DcMotor.Direction.FORWARD);
                status = "fast";
            }
//            telemetry.update();

            // POV Mode uses left stick to go forward, and right stick to turn.
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -leftSpeed, leftSpeed);
            rightPower = Range.clip(drive - turn, -rightSpeed  , rightSpeed);


            // Send calculated power to wheels
            leftDrive.setPower(-leftPower);
            rightDrive.setPower(-rightPower);
            spinner.setPower(spinnerSpeed);
            int pos = leftDrive.getCurrentPosition();


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("leftPos", pos);
            telemetry.addData("Motor status", status);
            telemetry.update();
        }
    }
}
