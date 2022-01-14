package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Drive System", group = "Linear Opmode")
//@Disabled
public class





DriveSystem extends LinearOpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor spinner = null;
    private DcMotor arm = null;
    private Servo leftClaw = null;
    private Servo rightClaw = null;
    private String status = "No status";
    private String speed = "No value";



    @Override
    public void runOpMode() {



        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive = hardwareMap.get(DcMotor.class, "left");
        rightDrive = hardwareMap.get(DcMotor.class, "right");
        spinner = hardwareMap.get(DcMotor.class, "spinner");
        arm = hardwareMap.get(DcMotor.class, "arm");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        rightClaw.setPosition(0);
        leftClaw.setPosition(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        spinner.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        boolean slow = false;
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
            double armSpeed = 1;
            //variable for claw position
            double lcPos = leftClaw.getPosition(); //Setting left Claw position
            double rcPos = rightClaw.getPosition(); //Setting Right Claw position



            //space where slowing code will go
            if(gamepad1.y){
                if(slow == false){
                    slow = true;
                } else {
                    slow = false;
                }
            }
            if (slow && gamepad1.a) {
                spinner.setDirection(DcMotor.Direction.FORWARD);
                leftSpeed = 0.5;
                rightSpeed = 0.5;
                spinnerSpeed = 0.5;
                status = "Spinner forward";
                speed = "Slow";
//                telemetry.addData("Spinner", "On and Front Spinning");
//                telemetry.addData("Speed", "Slow");
            } if (slow && gamepad1.b) {
                spinner.setDirection(DcMotor.Direction.REVERSE);
                leftSpeed = 0.5;
                rightSpeed = 0.5;
                spinnerSpeed = 0.5;
                status = "Spinner Backwards";
                speed = "Slow";
//                telemetry.addData("Spinner", "On and Front Spinning");
//                telemetry.addData("Speed", "Slow");
            } else if (slow) {
                leftSpeed = 0.5;
                rightSpeed = 0.5;
//                telemetry.addData("Speed", "Slow");
                speed = "slow";
            } else if (gamepad1.a) {
                spinner.setDirection(DcMotor.Direction.FORWARD);
                spinnerSpeed = 0.5;
//                telemetry.addData("Spinner", "On and Front Spinning");
                status = "Spinner forward";
                speed = "Fast";
            } else if (gamepad1.b) {
                spinner.setDirection(DcMotor.Direction.REVERSE);
                spinnerSpeed = 0.5;
//                telemetry.addData("Spinner", "On and Back Spinning");
                status = "spinner backwards";
                speed = "Fast";
            } else {
                leftSpeed = 1;
                rightSpeed = 1;
                spinnerSpeed = 0;
                spinner.setDirection(DcMotor.Direction.FORWARD);
                status = "drive";
                speed = "Fast";
//                telemetry.addData("Speed", "Fast");
            }

            //arm work -- might change in the future
            if(gamepad1.dpad_up){
                //move arm up
                arm.setDirection(DcMotorSimple.Direction.REVERSE);
                arm.setPower(1);
                status = "arm up";
//                telemetry.addData("status","arm up");
            }else if(gamepad1.dpad_down){
                //move claw down
                arm.setDirection(DcMotorSimple.Direction.FORWARD);
                arm.setPower(1);
                status = "arm down";
            } else {
                arm.setPower(0);
                status = null;
            }

            //These control the servos
            if (gamepad1.dpad_right) {
                leftClaw.setPosition(lcPos += 0.1);
                rightClaw.setPosition(rcPos -= 0.1);
            } else if (gamepad1.dpad_left) {
                leftClaw.setPosition(lcPos -= 0.1);
                rightClaw.setPosition(rcPos += 0.1);
            }

            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -leftSpeed, leftSpeed);
            rightPower = Range.clip(drive - turn, -rightSpeed, rightSpeed);


            // Send calculated power to wheels
            leftDrive.setPower(-leftPower);
            rightDrive.setPower(-rightPower);
            spinner.setPower(spinnerSpeed);
            arm.setPower(armSpeed);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Run time", runtime.toString());
            telemetry.addData("Status",status);
            telemetry.addData("speed",speed);
            telemetry.update();
        }
    }
}