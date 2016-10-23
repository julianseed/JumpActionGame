package jp.techacademy.yasuhiko.tokushima.jumpactiongame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by tokushima on 2016/10/23.
 */

public class Enemy extends GameObject {
    // 横幅、高さ
    public static final float ENEMY_WIDTH = 0.9f;
    public static final float ENEMY_HEIGHT = 0.9f;

    // タイプ（通常と動くタイプ）
    public static final int ENEMY_TYPE_STATIC = 0;
    public static final int ENEMY_TYPE_MOVING = 1;

    // 向き
    public static final int ENEMY_DIRECTION_RIGHT = 0;
    public static final int ENEMY_DIRECTION_LEFT = 1;

    // 速度
    public static final float ENEMY_VELOCITY = 1.2f;

    int mType;
    int mDirection = ENEMY_DIRECTION_RIGHT;

    public Enemy(int type, int direction, Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
        setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
        mType = type;
        if (mType == ENEMY_TYPE_MOVING) {
            velocity.x = ENEMY_VELOCITY;
        }
        mDirection = direction;
        if (mDirection == ENEMY_DIRECTION_RIGHT) {
            this.setFlip(true, false);
        } else {
            this.setFlip(false, false);
            velocity.x = -velocity.x;
        }
    }

    // 座標を更新する
    public void update(float deltaTime) {
        if (mType == ENEMY_TYPE_MOVING) {
            setX(getX() + velocity.x * deltaTime);

            if (getX() < ENEMY_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX(ENEMY_WIDTH / 2);

                // キャラの向きを変える
                if (velocity.x > 0) {
                    mDirection = ENEMY_DIRECTION_RIGHT;
                } else {
                    mDirection = ENEMY_DIRECTION_LEFT;
                }
            }
            if (getX() > GameScreen.WORLD_WIDTH - ENEMY_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX(GameScreen.WORLD_WIDTH - ENEMY_WIDTH / 2);

                // キャラの向きを変える
                if (velocity.x > 0) {
                    mDirection = ENEMY_DIRECTION_RIGHT;
                } else {
                    mDirection = ENEMY_DIRECTION_LEFT;
                }
            }
            if (mDirection == ENEMY_DIRECTION_RIGHT) {
                this.setFlip(true, false);
            } else {
                this.setFlip(false, false);
            }
        }
    }
}
