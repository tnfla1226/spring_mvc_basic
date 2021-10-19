<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style>
        .attach-file-list {
            display: flex;
            width: 80%;
            border: 1px dashed gray;
            margin: 20px auto;
            padding: 20px 10px;
        }

        .attach-file-list a {
            display: flex;
            flex-direction: column;
        }

        .attach-file-list a img {
            width: 100px;
            height: 100px;
            display: block;
        }

        .attach-file-list .thumbnail-box {
            display: flex;
        }
    </style>
    <%@ include file="../include/static-head.jsp" %>
</head>

<body>

    <%@ include file="../include/header.jsp" %>

    <div class="container">
        <div class="row">
            <div class="offset-md-1 col-md-10">
                <h1>${article.boardNo}번 게시물 내용</h1>

                <p>
                    # 글번호: ${article.boardNo}<br>
                    # 작성자: ${article.writer}<br>
                    # 제목: ${article.title}<br>
                    # 내용: <br>
                    <textarea rows="5" cols="30" disabled>${article.content}</textarea>
                </p>

                <a
                    href="/board/list?pageNum=${page.pageNum}&amount=${page.amount}&type=${maker.page.type}&keyword=$maker.page.keyword}">글
                    목록보기</a>&nbsp;

                <a href="/board/modify?boardNo=${article.boardNo}">글 수정하기</a>


            </div>
        </div>

        <!-- 첨부파일 영역 -->
        <div class="row">
            <div class="attach-file-list"></div>
        </div>

        <!-- 댓글 영역 -->

        <div id="replies" class="row">
            <div class="offset-md-1 col-md-10">
                <!-- 댓글 쓰기 영역 -->
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label for="newReplyText" hidden>댓글 내용</label>
                                    <textarea rows="3" id="newReplyText" name="replyText" class="form-control"
                                        placeholder="댓글을 입력해주세요."></textarea>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="newReplyWriter" hidden>댓글 작성자</label>
                                    <input id="newReplyWriter" name="replyWriter" type="text" class="form-control"
                                        placeholder="작성자 이름" style="margin-bottom: 6px;">
                                    <button id="replyAddBtn" type="button" class="btn btn-dark form-control">등록</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- end reply write -->

                <!--댓글 내용 영역-->
                <div class="card">
                    <!-- 댓글 내용 헤더 -->
                    <div class="card-header text-white m-0" style="background: #343A40;">
                        <div class="float-left">댓글 (<span id="replyCnt">0</span>)</div>
                    </div>

                    <!-- 댓글 내용 바디 -->
                    <div id="replyCollapse" class="card">
                        <div id="replyData">
                            <!-- 
                        < JS로 댓글 정보 DIV삽입 > 
                     -->
                        </div>

                        <!-- 댓글 페이징 영역 -->
                        <ul class="pagination justify-content-center">
                            <!-- 
                        < JS로 댓글 페이징 DIV삽입 > 
                     -->
                        </ul>
                    </div>
                </div> <!-- end reply content -->
            </div>
        </div> <!-- end replies row -->
    </div> <!-- end content container -->

    <!-- 댓글 수정 모달 -->
   <div class="modal fade bd-example-modal-lg" id="replyModifyModal">
    <div class="modal-dialog modal-lg">
       <div class="modal-content">

          <!-- Modal Header -->
          <div class="modal-header" style="background: #343A40; color: white;">
             <h4 class="modal-title">댓글 수정하기</h4>
             <button type="button" class="close text-white" data-bs-dismiss="modal">X</button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
             <div class="form-group">
                <input id="modReplyId" type="hidden">
                <label for="modReplyText" hidden>댓글내용</label>
                <textarea id="modReplyText" class="form-control" placeholder="수정할 댓글 내용을 입력하세요."
                   rows="3"></textarea>
             </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
             <button id="replyModBtn" type="button" class="btn btn-dark">수정</button>
             <button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
          </div>


       </div>
    </div>
 </div>

 <!-- end replyModifyModal -->


    <!-- 댓글 관련 스크립트 -->
    <script>
        //댓글 처리 js
        $(function () {

            //원본 글 번호
            const boardNo = '${article.boardNo}';

            //날짜 포맷 변환 함수
            function formatDate(datetime) {
                //문자열 날짜 데이터를 날짜객체로 변환
                const dateObj = new Date(datetime);
                // console.log(dateObj);
                //날짜객체를 통해 각 날짜 정보 얻기
                let year = dateObj.getFullYear();
                //1월이 0으로 설정되어있음.
                let month = dateObj.getMonth() + 1;
                let day = dateObj.getDate();
                let hour = dateObj.getHours();
                let minute = dateObj.getMinutes();

                //오전, 오후 시간체크
                let ampm = '';
                if (hour < 12 && hour >= 6) {
                    ampm = '오전';
                } else if (hour >= 12 && hour < 21) {
                    ampm = '오후';
                    if (hour !== 12) {
                        hour -= 12;
                    }
                } else if (hour >= 21 && hour <= 24) {
                    ampm = '밤';
                    hour -= 12;
                } else {
                    ampm = '새벽';
                }

                //숫자가 1자리일 경우 2자리로 변환
                (month < 10) ? month = '0' + month: month;
                (day < 10) ? day = '0' + day: day;
                (hour < 10) ? hour = '0' + hour: hour;
                (minute < 10) ? minute = '0' + minute: minute;

                return year + "-" + month + "-" + day + " " + ampm + " " + hour + ":" + minute;

            }


            //댓글 태그 생성, 배치 함수
            function makeReplyListDOM(replyList) {

                let tag = '';

                for (let reply of replyList) {
                    tag += "<div id='replyContent' class='card-body' data-replyId='" + reply.replyNo + "'>" +
                        "    <div class='row user-block'>" +
                        "       <span class='col-md-3'>" +
                        "         <b>" + reply.replyWriter + "</b>" +
                        "       </span>" +
                        "       <span class='offset-md-6 col-md-3 text-right'><b>" + formatDate(reply.replyDate) +
                        "</b></span>" +
                        "    </div><br>" +
                        "    <div class='row'>" +
                        "       <div class='col-md-6'>" + reply.replyText + "</div>" +
                        "       <div class='offset-md-2 col-md-4 text-right'>" +
                        "         <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;" +
                        "         <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>" +
                        "       </div>" +
                        "    </div>" +
                        " </div>";
                }

                //만든 태그를 댓글목록 안에 배치
                //document.querySelector('#replyData').innerHTML = tag;
                $('#replyData').html(tag);

            }

            //댓글 목록 비동기 요청 처리 함수
            function getReplyList() {
                fetch('/api/v1/reply/' + boardNo)
                    .then(res => res.json())
                    .then(replyList => {
                        console.log(replyList);
                        makeReplyListDOM(replyList);
                    });
            }

            //페이지 진입시 댓글목록 불러오기
            getReplyList();


            //=============================================================//
            //댓글 등록 처리
            $('#replyAddBtn').on('click', e => {

                const reqInfo = {
                    method: 'POST', //요청 방식
                    headers: { //요청 헤더 내용
                        'content-type': 'application/json'
                    },
                    //서버로 전송할 데이터 (JSON)
                    body: JSON.stringify({
                        boardNo: boardNo,
                        replyText: $('#newReplyText').val(),
                        replyWriter: $('#newReplyWriter').val()
                    })
                };
                fetch('/api/v1/reply', reqInfo)
                    .then(res => res.text())
                    .then(msg => {
                        if (msg === 'insertSuccess') {
                            getReplyList();
                            $('#newReplyText').val('');
                            $('#newReplyWriter').val('');
                        } else {
                            alert('댓글 등록에 실패했습니다.');
                        }
                    });

            });

            //====================== 댓글 수정 이벤트 ==========================//

            //댓글 수정 창 진입 이벤트
            const $modal = $('#replyModifyModal');
            $('#replyData').on('click', '#replyModBtn', e => {  //수정버튼에만 작동
                console.log('수정 창 버튼 클릭!')

                //기존 댓글 내용을 가져오기
                const originText = e.target.parentNode.previousElementSibling.textContent;
                console.log(originText);
                //해당 댓글번호가져오기
                const replyNo = e.target.parentNode.parentNode.parentNode.dataset.replyid;

                //댓글내용 모달에 넣어놓기
                $('#modReplyText').val(originText);
                //input hidden에 댓글번호 넣어놓기
                $('#modReplyId').val(replyNo);
            });

        });
    </script>

</body>

</html>