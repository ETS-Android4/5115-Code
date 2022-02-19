package org.firstinspires.ftc.teamcode.Mecanum;

/* This OpMode runs motors in the following configuration:
       ////            \\\\
      /F1/ ------------ \F2\
     //// |            | \\\\
          |            |
          |            |
     \\\\ |            | ////
      \R1\ ------------ /R2/
       \\\\            ////
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "pogbot drive", group="mecanum")
public class MecanumDrive extends LinearOpMode {

    private DcMotor arm = null;
    private Servo leftClaw = null;
    private Servo rightClaw = null;
    private double posLeft = 0;
    private double posRight = 1;
    private DcMotor spinner = null;


    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor F1 = hardwareMap.dcMotor.get("F1");
        DcMotor R1 = hardwareMap.dcMotor.get("R1");
        DcMotor F2 = hardwareMap.dcMotor.get("F2");
        DcMotor R2 = hardwareMap.dcMotor.get("R2");

        arm = hardwareMap.get(DcMotor.class, "arm");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        spinner = hardwareMap.get(DcMotor.class, "spinner");
        rightClaw.setPosition(posLeft);
        leftClaw.setPosition(posRight);
        spinner.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        boolean slow = false;
        String status = "";

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        F1.setDirection(DcMotorSimple.Direction.REVERSE);
        R1.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            if(slow){
                denominator *= 2;
            }
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            F1.setPower(frontLeftPower);
            R1.setPower(backLeftPower);
            F2.setPower(frontRightPower);
            R2.setPower(backRightPower);



            //Arm, claw, and spinner
            double lcPos = leftClaw.getPosition(); //Setting left Claw position
            double rcPos = rightClaw.getPosition(); //Setting Right Claw position


//            if(gamepad1.y && !slow) {
//                slow = true;
//            }else if(gamepad1.y && slow){
//                slow = false;
//            }



            if (gamepad1.dpad_up) {
                //move arm up
                arm.setDirection(DcMotorSimple.Direction.REVERSE);
                arm.setPower(0.8);
            } else if (gamepad1.dpad_down) {
                //move claw down
                arm.setDirection(DcMotorSimple.Direction.FORWARD);
                arm.setPower(0.8);
            } else {
                arm.setPower(0);
            }

            if(gamepad1.left_trigger > 0.9 && gamepad1.right_trigger >0.9) {
//                slow = true;

            } else if(gamepad1.left_trigger > 0){
                //move arm down
                arm.setDirection(DcMotorSimple.Direction.REVERSE);
                arm.setPower(gamepad1.left_trigger);
//                slow = false;


            } else if(gamepad1.right_trigger > 0){
                //Move arm up
                arm.setDirection(DcMotorSimple.Direction.FORWARD);
                arm.setPower(gamepad1.right_trigger);
//                slow = false;

            } else{
                arm.setPower(0);
//                slow = false;
            }



            //These control the servos
            if (gamepad1.dpad_right || gamepad1.right_bumper) {
                // close claw
                leftClaw.setPosition(lcPos -= 0.1);
                rightClaw.setPosition(rcPos += 0.1);
            } else if (gamepad1.dpad_left || gamepad1.left_bumper) {
                //open claw
                leftClaw.setPosition(lcPos += 0.1);
                rightClaw.setPosition(rcPos -= 0.1);
            }

            //Spinner controls
            if(gamepad1.a){
                spinner.setDirection(DcMotorSimple.Direction.FORWARD);
                spinner.setPower(0.7);

            } else if(gamepad1.b){
                spinner.setDirection(DcMotorSimple.Direction.REVERSE);
                spinner.setPower(0.7);

            } else{
                spinner.setPower(0);
            }

            while(gamepad1.back){
                F1.setPower(0.7);
                F2.setPower(0.7);
                R1.setPower(-0.7);
                R2.setPower(-0.7);
            }

            telemetry.addData("Status", status);
            telemetry.addData("Slow",slow);
            telemetry.addData("Servo pos", "Left claw: "+lcPos+"  Right claw: "+rcPos);
            telemetry.update();



        }
    }
}