package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

<<<<<<< HEAD
@Autonomous(name = "Blue Auto 1", group = "Linear Opmode")

public class BlueAuto extends LinearOpMode{
    DcMotor left;
    DcMotor right;
    DcMotor spinner;
    boolean ran = false;

    double speed = 0.6;

    public void runOpMode() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        spinner = hardwareMap.dcMotor.get("spinner");
        left.setDirection(DcMotor.Direction.REVERSE);
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        waitForStart();

        encoders(250, 250, "Forward 1");
        encoders(1000, -1000, "right turn");
        encoders(1800, 1800, "forward 2");
        encoders(100, 100, "fixing");
        spinner.setPower(0.4);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setPower(0.2); //keep pressure to the carousel
        right.setPower(0.3);
        sleep(5000);
        spinner.setPower(0);
        left.setPower(0);
        right.setPower(0);

        encoders(-500, -500, "reverse1");
        encoders(500, -500, "turn");
        encoders(-1000, -1000, "reverse");
        encoders(-400, 400, "turn");
        encoders(-4000, -4000, "reverse to warehouse");


        telemetry.addData("Status", "finished");
        telemetry.update();
        sleep(5000);


    }


    public void encoders(int targetLeft, int targetRight,String status){
        if(opModeIsActive()){
            left.setTargetPosition(targetLeft);
            right.setTargetPosition(targetRight);
            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            left.setPower(speed);
            right.setPower(speed);

            while (opModeIsActive() && left.isBusy() && right.isBusy()){
                telemetry.addData("Status", status);
                telemetry.addData("Encoder Position", "Left: "+left.getCurrentPosition()+" | Right: "+right.getCurrentPosition());
                telemetry.update();
                idle();
            }

            left.setPower(0);
            right.setPower(0);
=======
public class BlueAuto {

    @Autonomous(name = "Blue Auto 1", group = "Linear Opmode")
    public class RedAuto extends LinearOpMode {
        DcMotor left;
        DcMotor right;
        DcMotor spinner;
        boolean ran = false;

        double speed = 0.6;

        public void runOpMode() {
            left = hardwareMap.dcMotor.get("left");
            right = hardwareMap.dcMotor.get("right");
            spinner = hardwareMap.dcMotor.get("spinner");
            left.setDirection(DcMotor.Direction.REVERSE);
            spinner.setDirection(DcMotorSimple.Direction.REVERSE);
>>>>>>> origin/master

            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

<<<<<<< HEAD
            sleep(1000);
        }

    }
}
=======

            waitForStart();
        }
    }

}
>>>>>>> origin/master
