package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            waitForStart();
        }
    }

}
