# ElasticSearch
ElasticSearch and Spring Boot

# Tech Stack
- Java 17
- Docker
- Elasticsearch

# APIs
<style>
  th {
    text-align: left;
  }
  pre {
    white-space: pre-wrap;
  }
</style>

<table>
  <tr>
    <th style="width: 200px;">METHOD</th>
    <th style="width: 800px;">REQUEST</th>
    <th style="width: 500px;">DESCRIPTION</th>
    <th style="width: 500px;">EXAMPLE</th>
  </tr>
  <tr>
    <td>POST</td>
    <td>http://localhost:8080/api/v1/items</td>
    <td>If the item does not exist, the request will create the index and insert the item.</td>
    <td><pre>{
    "id": "99",
    "name": "test",
    "price": 1,
    "brand": "test",
    "category": "test"
}</pre></td>
  </tr>
  <tr>
    <td>POST</td>
    <td>http://localhost:8080/api/v1/items/init-index</td>
    <td>Bulk add provisioning by scanning in resources/data/items.json</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>http://localhost:8080/api/v1/items/findAll</td>
    <td>Find all items without pagination</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>http://localhost:8080/api/v1/items/allIndexes</td>
    <td>Get all items from all indexes with pagination (default 10)</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>http://localhost:8080/api/v1/items/getAllDataFromIndex/{items_index} --> @PathVariable = items_index</td>
    <td>Get all items from the index with pagination (default 10)</td>
  </tr>
</table>
