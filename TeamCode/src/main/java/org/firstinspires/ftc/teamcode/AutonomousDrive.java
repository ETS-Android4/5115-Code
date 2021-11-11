package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;


import org.firstinspires.ftc.robotcore.external.Func;



@Autonomous(name="Autonomous System", group="Autonomous ")
//@Disabled
public class AutonomousDrive extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor spinner;

     public void runOpMode() throws InterruptedException {
         leftDrive = hardwareMap.dcMotor.get("left");
         rightDrive = hardwareMap.dcMotor.get("right");
         spinner = hardwareMap.dcMotor.get("spinner");

         // You will need to set this based on your robot's
         // gearing to get forward control input to result in
         // forward motion.
         leftDrive.setDirection(DcMotor.Direction.REVERSE);

         // reset encoder counts kept by motors.
         leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         // set motors to run forward for 5000 encoder counts.
         leftDrive.setTargetPosition(100);
         rightDrive.setTargetPosition(100);

         // set motors to run to target encoder position and stop with brakes on.
         leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         telemetry.addData("Mode", "waiting");
         telemetry.update();

         // wait for start button.

         waitForStart();

         telemetry.addData("Mode", "running");
         telemetry.update();

         // set both motors to 25% power. Movement will start. Sign of power is
         // ignored as sign of target encoder position controls direction when
         // running to position.

         leftDrive.setPower(0.25);
         rightDrive.setPower(0.25);

         // wait while opmode is active and left motor is busy running to position.

         while (opModeIsActive() && leftDrive.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
         {
             telemetry.addData("encoder-fwd-left", leftDrive.getCurrentPosition() + "  busy=" + leftDrive.isBusy());
             telemetry.addData("encoder-fwd-right", rightDrive.getCurrentPosition() + "  busy=" + rightDrive.isBusy());
             telemetry.update();
             idle();
         }

         // set motor power to zero to turn off motors. The motors stop on their own but
         // power is still applied so we turn off the power.

         leftDrive.setPower(0.0);
         rightDrive.setPower(0.0);

         // wait 5 sec to you can observe the final encoder position.

         resetStartTime();

         while (opModeIsActive() && getRuntime() < 5)
         {
             telemetry.addData("encoder-fwd-left-end", leftDrive.getCurrentPosition());
             telemetry.addData("encoder-fwd-right-end", rightDrive.getCurrentPosition());
             telemetry.update();
             idle();
         }

         // From current position back up to starting point. In this example instead of
         // having the motor monitor the encoder we will monitor the encoder ourselves.

         leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

         leftDrive.setTargetPosition(0);
         rightDrive.setTargetPosition(0);

         // Power sign matters again as we are running without encoder.
         leftDrive.setPower(-0.25);
         rightDrive.setPower(-0.25);

         while (opModeIsActive() && leftDrive.getCurrentPosition() > leftDrive.getTargetPosition())
         {
             telemetry.addData("encoder-back-left", leftDrive.getCurrentPosition());
             telemetry.addData("encoder-back-right", rightDrive.getCurrentPosition());
             telemetry.update();
             idle();
         }

         // set motor power to zero to stop motors.

         leftDrive.setPower(0.0);
         rightDrive.setPower(0.0);

         resetStartTime();

         while (opModeIsActive() && getRuntime() < 5)
         {
             telemetry.addData("encoder-back-left-end", leftDrive.getCurrentPosition());
             telemetry.addData("encoder-back-right-end", rightDrive.getCurrentPosition());
             telemetry.update();
             idle();
         }
     }
}