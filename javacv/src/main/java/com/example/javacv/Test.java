package com.example.javacv;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.lang.Thread.MAX_PRIORITY;
import static org.bytedeco.ffmpeg.global.avutil.*;

/**
 * @author lz
 * @date 2019/10/8
 */
public class Test {
    public static void main(String[] args) {
        String rtsp[] = {"rtsp://admin:zh123456@10.168.1.9:554", "rtsp://admin:admin@10.168.1.43:554/av0_0", "rtsp://admin:zh123456@10.168.1.7:554", "rtsp://admin:zh123456@10.168.1.8:554", "rtsp://admin:zh123456@10.168.1.233:554"};
        for (int i = 0; i < rtsp.length; i++) {
            DetectHandle r = new DetectHandle(rtsp[i], i);
            Thread thread = new Thread(r);
            thread.start();
        }

    }

    private static class DetectHandle implements Runnable {

        private final String rtsp;
        private final Integer id;

        public DetectHandle(String rtsp, Integer id) {
            this.rtsp = rtsp;
            this.id = id;
        }

        @Override
        public void run() {
            //设置日志级别
            avutil.av_log_set_level(MAX_PRIORITY);
            FFmpegFrameGrabber streamGrabber = new FFmpegFrameGrabber(rtsp) {
                public int getPixelFormat() {
                    int pixFormat = super.getPixelFormat();
                    switch (pixFormat) {
                        case AV_PIX_FMT_YUVJ420P:
                            pixFormat = AV_PIX_FMT_YUV420P;
                            break;
                        case AV_PIX_FMT_YUVJ422P:
                            pixFormat = AV_PIX_FMT_YUV422P;
                            break;
                        case AV_PIX_FMT_YUVJ444P:
                            pixFormat = AV_PIX_FMT_YUV444P;
                            break;
                        case AV_PIX_FMT_YUVJ440P:
                            pixFormat = AV_PIX_FMT_YUV440P;
                        default:
                            break;
                    }
                    return pixFormat;
                }
            };
            streamGrabber.setOption("rtsp_transport", "tcp");
            streamGrabber.setFormat("rtsp");

            streamGrabber.setImageMode(FrameGrabber.ImageMode.COLOR);
            streamGrabber.setTimeout(5 * 1000);
            streamGrabber.setOption(TimeoutOption.STIMEOUT.getKey(), String.valueOf(5 * 500000));
            streamGrabber.setVideoCodec(avcodec.AV_CODEC_ID_H264);

            try {
                streamGrabber.start();
                System.out.println("启动连接...");
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }


            while (true) {
                try {
                    Frame frame = streamGrabber.grab();
                    if (frame == null || frame.image == null) {
                        System.out.println("图片抓取失败" + id);
                        streamGrabber.restart();
                        continue;
                    }
                    Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();

                    BufferedImage bufferedImage = java2DFrameConverter.getBufferedImage(frame);

                    File dir = new File("E:\\images\\" + id);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    synchronized (id) {
                        File output = new File(dir, System.currentTimeMillis() + ".png");
                        ImageIO.write(bufferedImage, "png", output);
                    }
                    System.out.println("抓取图片完成");
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(rtsp);
                }
            }
        }

    }


    /**
     * There is no universal option for streaming timeout. Each of protocols has
     * its own list of options.
     */
    private static enum TimeoutOption {
        /**
         * Depends on protocol (FTP, HTTP, RTMP, SMB, SSH, TCP, UDP, or UNIX).
         * <p>
         * http://ffmpeg.org/ffmpeg-all.html
         */
        TIMEOUT,
        /**
         * Protocols
         * <p>
         * Maximum time to wait for (network) read/write operations to complete,
         * in microseconds.
         * <p>
         * http://ffmpeg.org/ffmpeg-all.html#Protocols
         */
        RW_TIMEOUT,
        /**
         * Protocols -> RTSP
         * <p>
         * Set socket TCP I/O timeout in microseconds.
         * <p>
         * http://ffmpeg.org/ffmpeg-all.html#rtsp
         */
        STIMEOUT;

        public String getKey() {
            return toString().toLowerCase();
        }

    }
}


