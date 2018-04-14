package com.selfdev.googlemapstut;

import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.selfdev.googlemapstut.binarytree.BinaryTreeDemo;
import com.selfdev.googlemapstut.linkedlist.DoublyLinkedList;
import com.selfdev.googlemapstut.linkedlist.LinkedList;
import com.selfdev.googlemapstut.linkedlist.SinglyCircularLinkedList;
import com.selfdev.googlemapstut.queue.LLQueue;
import com.selfdev.googlemapstut.queue.MyQueue;
import com.selfdev.googlemapstut.queue.QueueDemo;
import com.selfdev.googlemapstut.sorting.SelectionSort;
import com.selfdev.googlemapstut.sorting.bubble.BubbleSort;
import com.selfdev.googlemapstut.stack.LLStack;
import com.selfdev.googlemapstut.stack.StackDemo;
import com.selfdev.googlemapstut.threadpool.STTest;

import java.io.IOError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static String TAG = "MapsActivity";
    private boolean isCameraZoomed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        /*LinkedList.SinglyLinkedList<String> stringSinglyLinkedList = new LinkedList().new SinglyLinkedList<>();
        stringSinglyLinkedList.addNewNode("A");
        stringSinglyLinkedList.addNewNode("B");
        stringSinglyLinkedList.addNewNode("C");
        stringSinglyLinkedList.addNewNode("D");
        stringSinglyLinkedList.addNewNode("E");
        stringSinglyLinkedList.addNewNode("F");
        stringSinglyLinkedList.addNewNode("G");

        LinkedList.SinglyLinkedList<Integer> integerSinglyLinkedList = new LinkedList().new SinglyLinkedList<>();
        integerSinglyLinkedList.addNewNode(1);
        integerSinglyLinkedList.addNewNode(2);
        integerSinglyLinkedList.addNewNode(3);
        integerSinglyLinkedList.addNewNode(4);
        integerSinglyLinkedList.addNewNode(5);
        integerSinglyLinkedList.addNewNode(6);*/

        //-------------------------------------------------

//        SinglyCircularLinkedList.CircularLinkedList<String> stringCircularLinkedList = new SinglyCircularLinkedList().new CircularLinkedList<>();

        /*stringCircularLinkedList.addNewNode("A");
        stringCircularLinkedList.addNewNode("b");
        stringCircularLinkedList.addNewNode("c");
        stringCircularLinkedList.addNewNode("d");
        stringCircularLinkedList.addNewNode("e");
        stringCircularLinkedList.printNodes();

        stringCircularLinkedList.addNewNode("BB");
        stringCircularLinkedList.addNewNode("CC");
        stringCircularLinkedList.printNodes();


        stringCircularLinkedList.addNodeAtHead("XXXX");
        stringCircularLinkedList.addNodeAtHead("ZZZZZ");
        stringCircularLinkedList.printNodes();


        stringCircularLinkedList.addNodeAtTail("T 1");
        stringCircularLinkedList.addNodeAtTail("T 2");
        stringCircularLinkedList.printNodes();
        stringCircularLinkedList.addNewNode("T BB");
        stringCircularLinkedList.addNewNode("T CC");
        stringCircularLinkedList.printNodes();
        stringCircularLinkedList.getNodeAt(17);*/

//        stringCircularLinkedList.traverse(stringCircularLinkedList.getNode(8));

        /*stringCircularLinkedList.getNodeAt(0);
        stringCircularLinkedList.getNodeAt(1);
        stringCircularLinkedList.getNodeAt(2);
        stringCircularLinkedList.getNodeAt(3);
        stringCircularLinkedList.getNodeAt(4);
        stringCircularLinkedList.getNodeAt(5);*/

        /*DoublyLinkedList.DoublyList<String> stringDoublyList = new DoublyLinkedList().new DoublyList<>();

        stringDoublyList.addNewNode("A");
        stringDoublyList.addNewNode("B");
        stringDoublyList.addNewNode("C");
        stringDoublyList.addNewNode("D");
        stringDoublyList.addNewNode("E");
        stringDoublyList.addNewNode("F");
        stringDoublyList.addNewNode("G");

        stringDoublyList.addNewNodeAtTail("G0");
        stringDoublyList.addNewNodeAtTail("G1");
        stringDoublyList.addNewNodeAtTail("G2");

        stringDoublyList.addNewNodeAtHead("A1");
        stringDoublyList.addNewNodeAtHead("A2");
        stringDoublyList.addNewNodeAtHead("A3");
        stringDoublyList.addNewNodeAtHead("A4");
*/
//        stringDoublyList.getNodeAt(0);
//        stringDoublyList.getNodeAt(1);
//        stringDoublyList.getNodeAt(2);
//        stringDoublyList.getNodeAt(5);

//        stringDoublyList.addNewNodeAt(0,"55");
//        stringDoublyList.print();

//        stringDoublyList.deleteNodeAt(14);
//        stringDoublyList.print();

        //-----------------------------

        /*QueueDemo queueDemo = new QueueDemo(6);

        queueDemo.peek();
        queueDemo.enqueue(1);
        queueDemo.enqueue(2);
        queueDemo.enqueue(3);
        queueDemo.enqueue(4);
        queueDemo.enqueue(5);
        queueDemo.enqueue(6);
        queueDemo.enqueue(7);

        queueDemo.print();

        queueDemo.dequeue();
        queueDemo.dequeue();
        queueDemo.dequeue();
        queueDemo.print();

        queueDemo.enqueue(55);
        queueDemo.enqueue(66);
        queueDemo.enqueue(77);

        queueDemo.print();*/

        //-----------------------------

        /*StackDemo stackDemo = new StackDemo(6);

        stackDemo.push(1);
        stackDemo.push(2);
        stackDemo.push(3);
        stackDemo.push(4);
        stackDemo.push(5);
        stackDemo.push(6);
        stackDemo.push(7);

        stackDemo.print();

        stackDemo.pop();
        stackDemo.pop();
        stackDemo.pop();
        stackDemo.pop();
        stackDemo.pop();



        stackDemo.print();*/

        //-----------------------------

        /*LLStack.Stack<String> llStack = new LLStack().new Stack();

        llStack.push("A");
        llStack.push("B");
        llStack.push("C");
        llStack.push("D");
        llStack.push("E");

        llStack.print();

        llStack.pop();
        llStack.pop();
        llStack.pop();
        llStack.pop();
        llStack.pop();
        llStack.pop();

        llStack.print();*/

        //-----------------------------

        /*LLQueue.Queue<String> stringQueue = new LLQueue().new Queue<>();

        stringQueue.enqueue("A");
        stringQueue.enqueue("B");
        stringQueue.enqueue("C");
        stringQueue.enqueue("D");
        stringQueue.enqueue("E");
        stringQueue.enqueue("F");
        stringQueue.peek();

        stringQueue.dequeue();
        stringQueue.dequeue();
        stringQueue.dequeue();
        stringQueue.peek();*/

        //-----------------------------
       /* BinaryTreeDemo.BinarySearchTree searchTree = new BinaryTreeDemo().new BinarySearchTree();
//        Log.e("BinaryTreeDemo", "onCreate: "+searchTree.find(11) );
        searchTree.insert(13);
        searchTree.insert(2);
        searchTree.insert(1);
        searchTree.insert(6);
        searchTree.insert(5);
        searchTree.insert(19);
        searchTree.insert(18);
        searchTree.insert(32);
        searchTree.insert(7);
        searchTree.insert(3);
        searchTree.insert(4);
        *//*
        searchTree.insert(12);
        searchTree.insert(3);
        searchTree.insert(2);*//*
        searchTree.inorder();
//        Log.e("BinaryTreeDemo", "onCreate: "+searchTree.find(13) );
        Log.e("BinaryTreeDemo", "onCreate: **********************" );
//        Log.e("BinaryTreeDemo", "onCreate: SUCC = "+searchTree.getSuccessor(searchTree.find(13)).data );
        searchTree.deleteKey(6);
        searchTree.inorder();*/
        //-----------------------------

        /*new SelectionSort().selectionSort();
        Log.e("SelectionSort", "onCreate: **********************" );
        int[] arr =  new SelectionSort().recursiveSelectionSort();
        for (int i=0; i<arr.length; i++) {
            Log.e("SelectionSort", "recursiveSelectionSort: "+i+" = "+arr[i] );
        }*/

//        BubbleSort bubbleSort = new BubbleSort();
//        bubbleSort.bubbleSort();

        /*StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.insert(0, "Hello");
        Log.e(TAG, "onCreate: SB = "+stringBuffer.toString() );
        stringBuffer.insert(2, "Rahul");
        Log.e(TAG, "onCreate: SB = "+stringBuffer.toString() );*/

        /*Log.e(TAG, "onCreate: 1 = "+ret(1) );
        Log.e(TAG, "onCreate: 2 = "+ret(1) );
        Log.e(TAG, "onCreate: 3 = "+ret(1) );
        Log.e(TAG, "onCreate: 4 = "+ret(0) );*/

        /*LinkedList.SinglyLinkedList<Integer> linkedList = new LinkedList().new SinglyLinkedList<Integer>();
        linkedList.addNewNode(10);
        linkedList.addNewNode(11);
        linkedList.addNewNode(12);
        linkedList.addNewNode(13);
        linkedList.addNewNode(13);
        linkedList.addNewNode(12);
        linkedList.addNewNode(11);
        linkedList.addNewNode(10);*/
//        linkedList.addNewNode(17);
//        linkedList.addNewNode(18);

//        linkedList.printList();

//        linkedList.deleteNodeAt(4);
//        linkedList.swapPairWise();
//        linkedList.printReverseWithoutReversing();
//        linkedList.makeFirstLast();
//        linkedList.reverseLinkedListIteratively();


//        linkedList.printListHeadData();

//        boolean isPalindrome = linkedList.checkPalindrome();
//        Log.e(TAG, "printList AFTER checkPalindrome: "+isPalindrome);
//        linkedList.printList();
//        linkedList.mergeSort();
//        linkedList.printList();
//        linkedList.mergeSortArray();

    }

    private int ret(int x) throws IOException {
        return 100/x;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.e(TAG, "onMapReady: ======= 1");
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 99);
        } else {
            fetchLocation();
        }

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
            }
        });
    }

    private void fetchLocation() {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); // two minute interval
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        showChangeLocationSettingsDialog(mLocationRequest);

    }

    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int PATTERN_GAP_LENGTH_PX = 12;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    //
// Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    private void addPolyLine() {
        Polyline polyline = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(new LatLng[]{new LatLng(12, 77.0),
                        new LatLng(12, 77.1),
                        new LatLng(12, 77.2),
                        new LatLng(12, 77.3),
                        new LatLng(12, 77.4),
                        new LatLng(12, 77.5),
                        new LatLng(12, 77.6),
                        new LatLng(12, 77.7),
                        new LatLng(12, 77.8),
                        new LatLng(12, 77.9)
                })
        );

        polyline.setStartCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        polyline.setEndCap(new ButtCap());
        polyline.setColor(Color.RED);
        polyline.setJointType(JointType.BEVEL);
        /*ArrayList<LatLng> latLngArrayList = new ArrayList<>();
        latLngArrayList.add(new LatLng(12, 77.3));
        polyline.setPoints(latLngArrayList);*/

        polyline.setPattern(PATTERN_POLYLINE_DOTTED);


    }

    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient fusedLocationProviderClient;
    List<Location> locationList = null;

    private List<Location> getFusedLocation() {

//        Log.e(TAG, "getFusedLocation: ");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest,new LocationCallback() {

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

//                Log.e(TAG, "onLocationResult: " + locationResult.getLocations());
                locationList = locationResult.getLocations();
                for (Location location : locationList) {
//                    Log.e(TAG, "onLocationResult: LAT = " + location.getLatitude());
//                    Log.e(TAG, "onLocationResult: LON = " + location.getLongitude());
                    if (locationList != null) {
                        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions
//                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                                .position(sydney)
                                .title("Custom");

                        mMap.addMarker(markerOptions);
                        if (!isCameraZoomed) {
                            isCameraZoomed = !isCameraZoomed;
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                        }

                        if (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        mMap.setMyLocationEnabled(true);
//                        setGroundOverLay(sydney);
//                        setTileOverLay();
                    }
                }
            }
        }, Looper.myLooper());
        return locationList;
    }

    private void continuousLocationUpdate(LocationRequest locationRequest) {
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                locationList = locationResult.getLocations();
                for (Location location : locationList) {
//                    Log.e(TAG, "onLocationResult: LAT = " + location.getLatitude());
//                    Log.e(TAG, "onLocationResult: LON = " + location.getLongitude());
                    if (locationList != null) {
                        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions
//                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                                .position(sydney)
                                .title("Custom");

//                        mMap.addMarker(markerOptions);
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
                        if (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        mMap.setMyLocationEnabled(true);
//                        setGroundOverLay(sydney);
//                        setTileOverLay();
                    }
                }
            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        }, Looper.myLooper());
    }


    private void showChangeLocationSettingsDialog(final LocationRequest locationRequest) {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Log.e(TAG, "onSuccess: -------------" );
                getFusedLocation();
//                continuousLocationUpdate(locationRequest);
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                try {
                    Log.e(TAG, "onFailure: -----------" );
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(MapsActivity.this,
                            99);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            }
        });

    }

    private void setGroundOverLay(LatLng latLng) {
        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.hamburger_ad_icon))
                .position(latLng, 1000f,1000f);
        mMap.addGroundOverlay(groundOverlayOptions);
    }

    private void setTileOverLay() {

        TileProvider tileProvider = new UrlTileProvider(256, 256) {
            @Override
            public URL getTileUrl(int x, int y, int zoom) {

                /* Define the URL pattern for the tile images */
                String s = String.format("https://www.elastic.co/assets/blt3541c4519daa9d09/maxresdefault.jpg",
                        zoom, x, y);

                if (!checkTileExists(x, y, zoom)) {
                    return null;
                }

                try {
                    return new URL(s);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
            }

            /*
             * Check that the tile server supports the requested x, y and zoom.
             * Complete this stub according to the tile range you support.
             * If you support a limited range of tiles at different zoom levels, then you
             * need to define the supported x, y range at each zoom level.
             */
            private boolean checkTileExists(int x, int y, int zoom) {
                int minZoom = 12;
                int maxZoom = 16;

                if ((zoom < minZoom || zoom > maxZoom)) {
                    return false;
                }

                return true;
            }
        };

        TileOverlay tileOverlay = mMap.addTileOverlay(new TileOverlayOptions()
                .tileProvider(tileProvider));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            }
        }
    }
}
