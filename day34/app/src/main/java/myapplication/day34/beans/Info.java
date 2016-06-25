package myapplication.day34.beans;

import android.os.Parcel;
import android.os.Parcelable;


public class Info implements Parcelable {

    public String title;
    public int infoclass;
    public String img;
    public String description;
    public String kewwords;
    public int fount;
    public int rcount ;
    public String time;
    private long id;


    public Info() {
    }
     protected  Info(Parcel in){

          img=in.readString();
         description=in.readString();
         rcount=in.readInt();
         time=in.readString();
         id=in.readLong();
     }
    public static final Parcelable.Creator<Info> CREATOR=new Parcelable.Creator<Info>(){


        @Override
        public Info createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[0];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {

        this.img = img;
    }

    @Override
    public String toString() {
        return "Info{" +
                "title='" + title + '\'' +
                ", infoclass=" + infoclass +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", kewwords='" + kewwords + '\'' +
                ", fount=" + fount +
                ", rcount=" + rcount +
                ", time='" + time + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest,int flags){
          dest.writeString(img);
          dest.writeString(description);
          dest.writeInt(rcount);
          dest.writeString(time);
          dest.writeLong(id);

  }


}
