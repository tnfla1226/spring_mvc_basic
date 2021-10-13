<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>

        .amount {
            width: 30%;
            display: flex;
            justify-content: flex-end;
            margin-bottom: 10px;
        }

        .amount a {
            display: block;
            color: #fff;
            background: #f00;
            width: 50px;
            height: 20px;
            border-radius: 5px;
            margin-right: 5px;
            text-align: center;
            font-weight: 700;
            text-decoration: none;
        }

        ul.pagination {
            width: fit-content;
            margin: 5px auto;
        }

        ul.pagination li.p-active a {
            background: orangered;
            color: #fff;
        }

    </style>
    <%@ include file="../include/static-head.jsp" %>


</head>

<body>

    <%@ include file="../include/header.jsp" %>


    <div class="list-container">
    <h2>게시글 목록</h2>

    <div class="amount">
        <a href="/board/list?amount=10">10</a>
        <a href="/board/list?amount=20">20</a>
        <a href="/board/list?amount=30">30</a>
      </div>

      <table class="table table-hover">
            <tr class="table-dark">
                <td>번호</td>
                <td>작성자</td>
                <td>제목</td>
                <td>작성일자</td>
                <td>조회수</td>
                <td>비고</td>
            </tr>


            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>${article.boardNo}</td>
                    <td>${article.writer}</td>
                    <td>
                        <a href="/board/content?boardNo=${article.boardNo}&pageNum=${maker.page.pageNum}&amount=${maker.page.amount}">${article.title}</a>
                        
                        <c:if test="${article.newFlag}">
                            <span class="badge rounded-pill bg-danger">New</span>
                        </c:if>
                    </td>
                    <td>
                        <fmt:formatDate value="${article.regDate}"
                        pattern="yyyy-MM-dd a hh:mm:ss" />
                    </td>
                    <td>${article.viewCnt}</td>
                    <td>
                        <a data-board-no = "${article.boardNo}" class="del-btn" href="#">[삭제]</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <!-- 페이지 영역 -->
        <ul class="pagination">
            <c:if test="${maker.prev}">
                <li class="page-item"><a class="page-link" href="/board/list?pageNum=${maker.beginPage - 1}&amount=${maker.page.amount}">Prev</a></li>
            </c:if>

            <c:forEach var="i" begin="${maker.beginPage}" end="${maker.endPage}" step="1">
                <li data-page="${i}" class="page-item"><a class="page-link" href="/board/list?pageNum=${i}&amount=${maker.page.amount}">${i}</a></li>
            </c:forEach>

            <c:if test="${maker.next}">
                <li class="page-item"><a class="page-link" href="/board/list?pageNum=${maker.endPage + 1}&amount=${maker.page.amount}">Next</a></li>
            </c:if>
        </ul>


        <!-- 검색창 영역 -->
        <div class="search">
            <form action="/board/list" id="search-form">

                <select name="type">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="writer">작성자</option>
                    <option value="titleContent">제목+내용
                    </option>
                </select>

                <input type="text" name="keyword" placeholder="검색어를 입력!" value="">

                <button type="submit">검색</button>

            </form>
        </div>

        <p>
            <a href="/board/write">게시글 작성하기</a>
        </p>
    </div>

        <script>
            //게시물 등록 처리 알림
            const msg = "${msg}";
            if (msg === 'success') {
                alert('게시물 등록 성공!')
            } else if (msg === 'fail') {
                alert('게시물 등록 실패!')
            }

            //삭제 버튼 클릭 이벤트
            const table = document.querySelector('table');

            table.addEventListener('click', e => {
                
                if (!e.target.matches('table a.del-btn')) return;

                e.preventDefault(); //a태그 링크이동기능 중지
                // console.log("삭제버튼 클릭됨!")

                const boardNo = e.target.dataset.boardNo;

                if (confirm('정말로 삭제하시겠습니까??')) {
                    location.href = '/board/delete?boardNo=' + boardNo;     //location.href는 링크 이동 기능
                }
            });

            //현재 위치한 페이지 li태그에 클래스 p-active를 부여하는 함수
            function appendPageActive(curPageNum) {
                const $ul = document.querySelector('.pagination');
                for (let $li of [...$ul.children]) {
                    //모든 li들 중에 data-page 속성값이 현재 요청페이지 번호와 같다면
                    if ($li.dataset.page === curPageNum) {
                        $li.classList.add('p-active');
                        break;
                    }
                }
            }
            
            //메인 실행부
            (function() {
                appendPageActive('${maker.page.pageNum}');
            })();

        </script>


</body>

</html>