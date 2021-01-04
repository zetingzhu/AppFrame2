package com.zzt.zxing.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zzt.zxing.library.ZXingView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.zzt.zxing.library.BarcodeType;
import com.zzt.zxing.library.QRCodeView;

public class TestScanActivity extends CheckPermissionsActivity implements QRCodeView.Delegate {
    private static final String TAG = TestScanActivity.class.getSimpleName();

    private ZXingView mZXingView;

    @Override
    public void permissionsSuccess() {
        startReadQRCode();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scan);
        mZXingView = findViewById(R.id.zxingview);
        mZXingView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isHasPermissions()) {
            startReadQRCode();
        }
    }

    public void startReadQRCode() {
        if (mZXingView != null) {
            mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
            mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
        }
    }


    @Override
    protected void onStop() {
        if (mZXingView != null) {
            mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mZXingView != null) {
            mZXingView.onDestroy(); // 销毁二维码扫描控件
        }
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        setTitle("扫描结果为：" + result);
//        mZXingView.startSpot(); // 开始识别
        Intent intent = new Intent();
        intent.putExtra("qrcode", result);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    //
    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
        Toast.makeText(TestScanActivity.this, "打开相机出错", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_preview:
                mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
                break;
            case R.id.stop_preview:
                mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
                break;
            case R.id.start_spot:
                mZXingView.startSpot(); // 开始识别
                break;
            case R.id.stop_spot:
                mZXingView.stopSpot(); // 停止识别
                break;
            case R.id.start_spot_showrect:
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且开始识别
                break;
            case R.id.stop_spot_hiddenrect:
                mZXingView.stopSpotAndHiddenRect(); // 停止识别，并且隐藏扫描框
                break;
            case R.id.show_scan_rect:
                mZXingView.showScanRect(); // 显示扫描框
                break;
            case R.id.hidden_scan_rect:
                mZXingView.hiddenScanRect(); // 隐藏扫描框
                break;
            case R.id.decode_scan_box_area:
                mZXingView.getScanBoxView().setOnlyDecodeScanBoxArea(true); // 仅识别扫描框中的码
                break;
            case R.id.decode_full_screen_area:
                mZXingView.getScanBoxView().setOnlyDecodeScanBoxArea(false); // 识别整个屏幕中的码
                break;
            case R.id.open_flashlight:
                mZXingView.openFlashlight(); // 打开闪光灯
                break;
            case R.id.close_flashlight:
                mZXingView.closeFlashlight(); // 关闭闪光灯
                break;
            case R.id.scan_one_dimension:
                mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
                mZXingView.setType(BarcodeType.ONE_DIMENSION, null); // 只识别一维条码
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.scan_two_dimension:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.TWO_DIMENSION, null); // 只识别二维条码
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.scan_qr_code:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.ONLY_QR_CODE, null); // 只识别 QR_CODE
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.scan_code128:
                mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
                mZXingView.setType(BarcodeType.ONLY_CODE_128, null); // 只识别 CODE_128
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.scan_ean13:
                mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
                mZXingView.setType(BarcodeType.ONLY_EAN_13, null); // 只识别 EAN_13
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.scan_high_frequency:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.HIGH_FREQUENCY, null); // 只识别高频率格式，包括 QR_CODE、UPC_A、EAN_13、CODE_128
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.scan_all:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.ALL, null); // 识别所有类型的码
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.scan_custom:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式

                Map<DecodeHintType, Object> hintMap = new EnumMap<>(DecodeHintType.class);
                List<BarcodeFormat> formatList = new ArrayList<>();
                formatList.add(BarcodeFormat.QR_CODE);
                formatList.add(BarcodeFormat.UPC_A);
                formatList.add(BarcodeFormat.EAN_13);
                formatList.add(BarcodeFormat.CODE_128);
                hintMap.put(DecodeHintType.POSSIBLE_FORMATS, formatList); // 可能的编码格式
                hintMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE); // 花更多的时间用于寻找图上的编码，优化准确性，但不优化速度
                hintMap.put(DecodeHintType.CHARACTER_SET, "utf-8"); // 编码字符集
                mZXingView.setType(BarcodeType.CUSTOM, hintMap); // 自定义识别的类型

                mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                break;
            case R.id.choose_qrcde_from_gallery:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mZXingView != null) {
            mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
        }
    }


}