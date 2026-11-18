const contentContainer = document.getElementById('markdown-content');
const filenameHeader = document.getElementById('current-filename');
const fileList = document.getElementById('file-list');

async function loadFiles() {
    const response = await fetch('/docs/MarkdownFiles.json')
    const fileNames = await response.json();

    const notesFolderName = "Notes-Study-Guides";
    const weekTwoFolderName = "Week-2";

    const notesAndStudyGuides = fileNames[notesFolderName];
    const weekTwo = fileNames[weekTwoFolderName];

    for (const notesFile of notesAndStudyGuides) {
        const button = document.createElement('button');
        button.classList.add('list-group-item', 'list-group-item-action');
        button.textContent = notesFile;
        button.setAttribute('data-file', `docs/${notesFolderName}/${notesFile}`);
        button.addEventListener('click', () => {
            loadMarkdownFile(`docs/${notesFolderName}/${notesFile}`);
        });

        fileList.appendChild(button);
    }
}

loadFiles();

async function loadMarkdownFile(filePath) {
    try {
        filenameHeader.textContent = filePath;
        contentContainer.innerHTML = '<p class="text-muted">Loading...</p>';

        const response = await fetch(filePath);
        if (!response.ok) {
            throw new Error(`Failed to load ${filePath} (HTTP ${response.status})`);
        }

        const rawMarkdown = await response.text();

        // Parse Markdown and sanitize HTML to prevent XSS
        const parsedHtml = marked.parse(rawMarkdown);

        contentContainer.innerHTML = DOMPurify.sanitize(parsedHtml);
        console.log(contentContainer);
    } catch (error) {
        contentContainer.innerHTML = `
      <div class="alert alert-danger" role="alert">
        <strong>Error:</strong> ${error.message}
      </div>`;
    }
}

loadMarkdownFile('docs/README.md');