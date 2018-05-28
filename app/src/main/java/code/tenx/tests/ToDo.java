package code.tenx.tests;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "todo_table")
public class ToDo implements Parcelable{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int _id;
    @NonNull
    @ColumnInfo(name = "task")
    public String mTask;
    @NonNull
    @ColumnInfo(name = "start_time")
    public String mStartTime;
    @ColumnInfo(name = "end_time")
    public String mEndTime;

    public ToDo(@NonNull String mTask, @NonNull String mStartTime, String mEndTime) {
        this.mTask = mTask;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
    }

    @Ignore
    public ToDo(){

    }

    protected ToDo(Parcel in) {
        _id = in.readInt();
        mTask = in.readString();
        mStartTime = in.readString();
        mEndTime = in.readString();
    }

    public static final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    public int get_id() {
        return _id;
    }

    public void set_id(@NonNull int _id) {
        this._id = _id;
    }

    @NonNull
    public String getmTask() {
        return mTask;
    }

    @NonNull
    public String getmStartTime() {
        return mStartTime;
    }

    public String getmEndTime() {
        return mEndTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(mTask);
        dest.writeString(mStartTime);
        dest.writeString(mEndTime);
    }
}
