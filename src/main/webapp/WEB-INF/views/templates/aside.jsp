<aside>
  <div class="side_bar">
    <div class="sidebar_content">
      <ul>
        <li id="side_01">
          <div class="chat_btn">
            <a href="<%= request.getContextPath() %>/user" onclick="openPopup(event, '<%= request.getContextPath() %>/user')">
              <img src="/hairball/images/chat_icon.png" alt="chat">
            </a>
          </div>
        </li>
      </ul>
    </div>
  </div>
</aside>

<script>
  function openPopup(event, url) {
    event.preventDefault();
    window.open(url, "popupWindow", "width=401,height=575");
  }
</script>
