package css.cis3334.fishlocatorfirebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dchock on 4/20/2017.
 */

public class FishFirebaseData {

    DatabaseReference FishDbRef;
    public static final String FishDataTag = "Fish Data";

    public DatabaseReference open()  {
        // Get an instance of the database and a reference to the fish data in it
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FishDbRef = database.getReference(FishDataTag);
        return FishDbRef;
    }

    public void close() {

    }

    public Fish createFish( String species, String weightInOz, String dateCaught) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = FishDbRef.child(FishDataTag).push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(key, species, weightInOz, dateCaught);
        // ---- write the vote to Firebase
        FishDbRef.child(key).setValue(newFish);
        return newFish;
    }

    public Fish createFish( String species, String weightInOz, String dateCaught, String locationLatitude, String locationLongitude) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = FishDbRef.child(FishDataTag).push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(key, species, weightInOz, dateCaught, locationLatitude, locationLongitude);
        // ---- write the vote to Firebase
        FishDbRef.child(key).setValue(newFish);
        return newFish;
    }

    public void deleteFish(Fish fish) {
    String key = fish.getKey();
        FishDbRef.child(key).removeValue();
    }

    public List<Fish> getAllFish(DataSnapshot dataSnapshot) {
        List<Fish> fishlist = new ArrayList<Fish>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Fish fish = data.getValue(Fish.class);
            fishlist.add(fish);
        }

        return fishlist;
    }

}
