# Jungmin_Practice
처음 안드로이드를 배울 때 했던 과제들을 다시 해보고 실력을 탄탄히 다지기 위한 스터디


# WEEK1

## ⭐ view와 viewgroup

- viewgroup은 자식으로 뷰와 뷰그룹을 가질 수 있다
- view는 자식을 가질 수 없고 종류에는 ConstraintLayout, LinearLayout, RelativeLayout 등이 있다

## ⭐Layout 3가지

### 1. ConstraintLayout(실질적으로 항상 쓰이는 레이아웃)

- 가로와 세로 모두 상대적 제약조건이 걸려있어야 한다
- 여백은 margin으로 준다
- 중앙에 위치시키기 위해서는 양쪽으로 제약주기
- match_constraint==0dp
- chain, guideline을 이용해 올 위치 정해줄 수 있다

### 2. LinearLayout

- orientation의 종류는 horizontal(이게 default)과 vertical이 있다

### 3. RelativeLayout

- 상대적 위치를 지정하는 방법이다
- id값을 이용해 위치를 지정한다

한줄 입력제한과 비밀번호에 "*" 표시는 inputtype을 이용해 정한다
constraintDimentionRatio 이용해서 비율을 설정할 수 있다

## ⭐ 자동로그인과 SharedPreferences

### 1. Toast를 이용해 알림띄우기

.kt

```kotlin
Toast.makeText(this, "자동 로그인되었습니다", Toast.LENGTH_SHORT).show()
```

### 2. 회원가입 후 아이디와 비밀번호 가져오기

Login.kt(회원가입 버튼눌렀을때 회원가입페이지로 전환)

```kotlin
to_signup.setOnClickListener{
            var intent= Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent,1)
        }
```

Signup.kt(회원가입 완료시 로그인 페이지로 전환)

```kotlin
var intent= Intent(this, LoginActivity::class.java)
intent.putExtra("email", editText.text.toString())
intent.putExtra("password", editText4.text.toString())
setResult(Activity.RESULT_OK, intent)
```

Login.kt(가지고 온 결과로 띄우기)

```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                1->{
                    editText.setText(data!!.getStringExtra("email"))
                    editText2.setText(data!!.getStringExtra("password"))
                }
            }

        }

    }
```

### 3. SharedPreferences 이용해서 자동로그인

App.kt

```kotlin
class App: Application() {
    override fun onCreate(){
        super.onCreate()
        MySharedPreferences.init(this)
    }
}
```

MySharedPreferences.kt

```kotlin
object MySharedPreferences {
    private const val NAME = "trace.trace_study"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // SharedPreferences variables
    private val IS_LOGIN = Pair("is_login", false) //자동로그인 여부
    private val EMAIL = Pair("email", "")
    private val PASSWORD = Pair("password", "")

    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    // an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    // 값 가져오기 / 변경하기. 걍 MySharedPreferences.email 이런 식으로 값 가져오고 바로 변경 가능
    var islogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }

    var email: String
        get() = preferences.getString(EMAIL.first, EMAIL.second) ?: ""
        set(value) = preferences.edit {
            it.putString(EMAIL.first, value)
        }

    var password: String
        get() = preferences.getString(PASSWORD.first, PASSWORD.second) ?: ""
        set(value) = preferences.edit {
            it.putString(PASSWORD.first, value)
        }

}
```

mainactivity.kt(사용할 곳에서 데이터 저장/사용)

```kotlin
MySharedPreferences.islogin = true
MySharedPreferences.email = editText.text.toString()
MySharedPreferences.password = editText2.text.toString()

if (MySharedPreferences.islogin) {
            //내용
        }
```

# WEEK2

## ⭐라이브러리 추가

AndroidManifest.xml

```xml
apply plugin: 'kotlin-kapt'

//Dependencies 안에 넣을 내용

//리사이클러뷰
implementation 'androidx.recyclerview:recyclerview:1.1.0'
//material디자인 라이브러리
implementation "com.google.android.material:material:1.2.0-alpha05"
//이미지 로딩 라이브러리 : glide
implementation "com.github.bumptech.glide:glide:4.10.0"
kapt "com.github.bumptech.glide:compiler:4.10.0"
//동그란 이미지 커스텀 뷰 라이브러리 : https://github.com/hdodenhof/CircleImageView
implementation 'de.hdodenhof:circleimageview:3.1.0'
```

## ⭐상단바 custom 적용

values.styles.xml

```xml
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
```

activity_main.xml

```xml
<com.google.android.material.appbar.AppBarLayout
android:id="@+id/appBarLayout"
android:layout_width="match_parent"
android:layout_height="wrap_content"
app:layout_constraintTop_toTopOf="parent">
<androidx.appcompat.widget.Toolbar
android:id="@+id/main_toolbar"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:background="@color/colorPrimary">
<TextView
android:textSize="18sp"
android:textStyle="bold"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="@string/app_name"
android:textColor="@color/white"
android:gravity="center"
android:maxEms="15"/>
</androidx.appcompat.widget.Toolbar>
</com.google.android.material.appbar.AppBarLayout>
```

## ⭐ 하단 네비게이션 바와 뷰페이져

### 1. Bottom Navigation

activity_main.xml

```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNavigationView"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:background="@color/colorPrimary"
        app:itemIconTint="@color/bottom_selector"
        app:itemTextColor="@color/bottom_selector"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:menu="@menu/navigation" />
```

menu.navigation.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto">
<item android:id="@+id/menu_home"
android:icon="@drawable/ic_home_white"
android:title="Home"/>
<item android:id="@+id/menu_book"
android:icon="@drawable/ic_book_white"
android:title="Book"/>
<item android:id="@+id/menu_person"
android:icon="@drawable/ic_person_white"
android:title="MyPage"/>
</menu>
```

Color.botton_selector.xml

```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/colorWhite" android:state_selected="true"/>
    <item android:color="#d9d9d9" android:state_selected="false"/>

</selector>
```

### 2. ViewPager

activity_main.xml

```xml
<androidx.viewpager.widget.ViewPager
        android:id="@+id/main_viewPager"
        android:layout_width="409dp"
        android:layout_height="0dp"
        android:layout_marginTop="1dp"
        android:layout_marginBottom="1dp"
        app:layout_constraintBottom_toTopOf="@+id/bottomNavigationView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/appBarLayout" />
```

- layout과 java폴더에 fragment생성(fragment는 activity와 유사하지만 관리에 더 유익)
- activity_main에 생성한 viewpager와 fragment들을 adapter로 연결

MainPagerAdapter.kt

```kotlin
class MainPagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->HomeFragment()
            1->BookFragment()
            else->MyFragment()
        }
    }

    override fun getCount()=3

}
```

MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_viewPager.adapter=MainPagerAdapter(supportFragmentManager)
        main_viewPager.offscreenPageLimit=2
        main_viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked=true
            }

        })

        bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menu_home->main_viewPager.currentItem=0
                R.id.menu_book->main_viewPager.currentItem=1
                R.id.menu_person->main_viewPager.currentItem=2
            }
        }

    }
}
```

## ⭐ RecyclerView

### 🔥필수 포함요소

- 반복될 item
- data class
- RecyclerView 생성위치
- Adapter
- ViewHolder
- +그리고 데이터

item_insta.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/constraintLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/colorPrimary"
        android:paddingHorizontal="24dp"
        android:paddingVertical="8dp"
        app:layout_constraintTop_toTopOf="parent">

        <de.hdodenhof.circleimageview.CircleImageView
            android:id="@+id/img_profile"
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:src="@drawable/jungminpic"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <TextView
            android:id="@+id/tv_username"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="8dp"
            android:text="TextView"
            android:textColor="@color/colorWhite"
            android:textSize="16sp"
            android:textStyle="bold"
            app:layout_constraintBottom_toBottomOf="@+id/img_profile"
            app:layout_constraintStart_toEndOf="@+id/img_profile"
            app:layout_constraintTop_toTopOf="@+id/img_profile" />

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="@+id/tv_username"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="@+id/tv_username"
            android:src="@drawable/ic_more_white" />
    </androidx.constraintlayout.widget.ConstraintLayout>

    <ImageView
        android:id="@+id/img_contents"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:scaleType="centerCrop"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintDimensionRatio="1:1"
        app:layout_constraintTop_toBottomOf="@+id/constraintLayout"
        android:src="@drawable/ic_launcher_background" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

fragment_home.xml

```xml
<androidx.recyclerview.widget.RecyclerView
        android:layout_marginLeft="20dp"
        android:layout_marginRight="20dp"
        android:paddingTop="10dp"
        android:paddingBottom="10dp"
        android:id="@+id/rv_home"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:clipToPadding="false"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        tools:listitem="@layout/item_insta"
        />
```

InstaData.kt

```kotlin
data class InstaData (
    val userName: String,
    val img_profile: String,
    val img_contents: String
)
```

InstaAdapter.kt

```kotlin
class InstaAdapter(private val context: Context) :RecyclerView.Adapter<InstaViewHolder>(){
    var datas= mutableListOf<InstaData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_insta,parent,false)
        return InstaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}
```

InstaViewHolder.kt

```kotlin
class InstaViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val tv_username = itemView.findViewById<TextView>(R.id.tv_username)
    val img_profile = itemView.findViewById<ImageView>(R.id.img_profile)
    val img_contents = itemView.findViewById<ImageView>(R.id.img_contents)

    fun bind(instaData: InstaData){
        tv_username.text=instaData.userName
        Glide.with(itemView).load(instaData.img_profile).into(img_profile)
        Glide.with(itemView).load(instaData.img_contents).into(img_contents)

    }

}
```

homeFragment.kt

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instaAdapter= InstaAdapter(view.context)
        rv_home.adapter=instaAdapter
        rv_home.addItemDecoration(Itemdecoration(10))
        loadDatas()
    }

    private fun loadDatas(){
        datas.apply {
            add(
                InstaData(
                    userName = "이정민",
                    img_profile = "data:image/png;base64,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",
                    img_contents = "data:image/jpeg;base64,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"

                )
            )
        }
        instaAdapter.datas=datas
        instaAdapter.notifyDataSetChanged()

    }
}
```

## ⭐ 부가내용

### 1. itemDecoration

- RecyclerView에서 item들 사이에 간격을 띄어주는 역할을 한다.
- RecyclerView.ItemDecoration class를 상속받아서 사용한다.
- 적용할곳(이번경우에는 HomeFragment.kt)에 rv_home.addItemDecoration(ItemDecoration(10)) 과 같은 형태로 적용한다.

ItemDecoration.kt

```kotlin
class Itemdecoration(private val mSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = mSpace
        outRect.left= mSpace
        outRect.right= mSpace
        outRect.top= mSpace
    }
}
```

### 2. clipToPadding

- 일반적인 padding을 적용한다면 화면에 보이는 기준으로 위, 아래에 padding이 생기고 스크롤이 동작할 수 있는 크기가 작아진다(clipToPadding=true)
- clipToPadding을 사용한다면 설정에 따라 스크롤의 가장 위 혹은 가장 아래에만 padding이 생기게 된다(clipToPadding=false)

fragment_home.xml

```xml
//recyclerView 내부
android:clipToPadding="false"
```

### 3. recyclerview→grid style

- 나머지는 recyclerView와 동일 layout만 변화를 준 것

    fragment_home.xml

    ```xml
    //recyclerView 내부
    app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
    app:spanCount="3"
    ```
