# API Documentation

<h1>Endpoint</h1>
<p><a href="https://femeowrun-3m2kqa2azq-uc.a.run.app" target="_blank" rel="noopener">https://femeowrun-3m2kqa2azq-uc.a.run.app/</a></p>

<h1>Register</h1>
<ul>
	<li>URL
		<ul>
			<li><code>/register</code></li>
		</ul>
	</li>
  <li>Request Body
  <ul>
    <li><code>username</code> as <code>string</code></li>
    <li><code>password</code> as <code>string</code></li>
    <li><code>nama_lengkap</code> as <code>string</code></li>
    <li><code>phoneNumber</code> as <code>string</code></li>
    <li><code>location</code> as <code>string</code></li>
  </ul>
	</li>
	<li>Method
		<ul>
			<li>POST</li>
		</ul>
	</li>
	<li>Response<pre v-pre="" data-lang="json"><code class="lang-json">{
  "error": false,
  "message": "success"
}</code></pre></li>
</ul>

<h1>Login</h1>
<ul>
	<li>URL
		<ul>
			<li><code>/login</code></li>
		</ul>
	</li>
  <li>Request Body
  <ul>
    <li><code>username</code> as <code>string</code></li>
    <li><code>password</code> as <code>string</code></li>
  </ul>
	</li>
	<li>Method
		<ul>
			<li>POST</li>
		</ul>
	</li>
	<li>Response<pre v-pre="" data-lang="json"><code class="lang-json">{
  "error": false,
    "loginResult": {
        "location": "Gombong",
        "nama_lengkap": "M Lathif Harizky",
        "password": "123",
        "phoneNumber": "085447521356",
        "username": "iik1412"
    },
    "message": "success"
}</code></pre></li>
</ul>

<h1>Get All Forum</h1>
<ul>
	<li>URL
		<ul>
			<li><code>/forum</code></li>
		</ul>
	</li>
	<li>Method
		<ul>
			<li>GET</li>
		</ul>
	</li>
	<li>Response<pre v-pre="" data-lang="json"><code class="lang-json">{
  "error": false,
    "getForumResult": [
        {
            "body": "gambar",
            "breed": "anggora",
            "createdBy": "danala04",
            "dateCreated": "Sun, 29 May 2022 00:00:00 GMT",
            "haveImage": 1,
            "idPost": 3,
            "imageLink": "https://storage.googleapis.com/femeowstorage/tes.jpg",
            "nama_lengkap": "Daffa Nabil Libriana",
            "title": "gambar"
        },
        {
            "body": "tanpa gambar",
            "breed": "persia",
            "createdBy": "iik1412",
            "dateCreated": "Mon, 30 May 2022 00:00:00 GMT",
            "haveImage": 0,
            "idPost": 4,
            "imageLink": "",
            "nama_lengkap": "M Lathif Harizky",
            "title": "tanpa gambar"
        }
    ],
    "message": "success"
}</code></pre></li>
</ul>

<h1>Add New Forum</h1>
<ul>
	<li>URL
		<ul>
			<li><code>/forum</code></li>
		</ul>
	</li>
	<li>Request Body
		<ul>
			<li><code>title</code> as <code>string</code></li>
			<li><code>body</code> as <code>string</code></li>
		      <li><code>breed</code> as <code>string</code>, optional</li>
		      <li><code>createdBy</code> as <code>string</code></li>
		      <li><code>dateCreated</code> as <code>string</code>, example <code>yy-mm-dd</code></li>
		      <li><code>file</code> as <code>image</code>, optional</li>
		</ul>
	</li>
	<li>Method
		<ul>
			<li>POST</li>
		</ul>
	</li>
	<li>Response<pre v-pre="" data-lang="json"><code class="lang-json">{
  "error" : false,
  "message" : "success",
}</code></pre></li>
</ul>

<h1>Get Comment</h1>
<ul>
	<li>URL
		<ul>
			<li><code>/comment</code></li>
		</ul>
	</li>
	<li>Parameters
		<ul>
			<li><code>idPost</code> as <code>int</code></li>
		</ul>
	</li>
	<li>Method
		<ul>
			<li>GET</li>
		</ul>
	</li>
	<li>Response<pre v-pre="" data-lang="json"><code class="lang-json">{
  "error": false,
  "getCommentResult": [
    {
      "body": "tes komen",
      "createdBy": "iik1412",
      "dateCreated": "Tue, 31 May 2022 00:00:00 GMT",
      "idPost": 3,
      "nama_lengkap": "M Lathif Harizky"
    },
    {
      "body": "bales",
      "createdBy": "danala04",
      "dateCreated": "Wed, 01 Jun 2022 00:00:00 GMT",
      "idPost": 3,
      "nama_lengkap": "Daffa Nabil Libriana"
    },
    {
      "body": "tes komen 3",
      "createdBy": "danala04",
      "dateCreated": "Fri, 03 Jun 2022 00:00:00 GMT",
      "idPost": 3,
      "nama_lengkap": "Daffa Nabil Libriana"
    }
  ],
  "message": "success"
}</code></pre></li>
</ul>

<h1>Add New Comment</h1>
<ul>
	<li>URL
		<ul>
			<li><code>/comment</code></li>
		</ul>
	</li>
	<li>Request Body
		<ul>
			<li><code>body</code> as <code>string</code></li>
		      <li><code>createdBy</code> as <code>string</code></li>
		      <li><code>dateCreated</code> as <code>string</code>, example <code>yy-mm-dd</code></li>
		      <li><code>idPost</code> as <code>int</code></li>
		</ul>
	</li>
	<li>Method
		<ul>
			<li>POST</li>
		</ul>
	</li>
	<li>Response<pre v-pre="" data-lang="json"><code class="lang-json">{
  "error" : false,
  "message" : "success",
}</code></pre></li>
</ul>

<h1>Database Architecture</h1>

![image](https://user-images.githubusercontent.com/87354757/171211122-232af36d-2157-4489-91fe-3d7edd2d19aa.png)
