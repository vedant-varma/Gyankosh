import os
import tkinter as tk
from tkinter import filedialog, messagebox, Entry
from PIL import Image, ImageTk
import pytesseract
import openai

openai.api_key = "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"

BASE_DIR = "C:\\Users\\varma\\PycharmProjects\\RamayanTest\\Scriptures\\Valmiki Ramayan"  # Replace with your path

def get_insight(verse):
    messages = [
        {"role": "system",
         "content": "You are an assistant providing analysis of verses from the Valmiki Ramayan as well as giving a background."},
        {"role": "user",
         "content": f"Based on the following verse:\n{verse}"},
    ]

    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo", messages=messages
    )
    return response.choices[0].message['content']

def general_questions(event):
    question = general_question_widget.get("1.0", tk.END).strip()
    if question:
        insight = get_insight(question)
        messagebox.showinfo("Answer", insight)

def upload_image():
    file_path = filedialog.askopenfilename()
    if not file_path:
        return

    image = Image.open(file_path)
    photo = ImageTk.PhotoImage(image)

    canvas.image = photo
    canvas.config(scrollregion=canvas.bbox(tk.ALL))
    canvas.create_image(0, 0, anchor=tk.NW, image=photo)

    extracted_text = pytesseract.image_to_string(image, lang="hin")
    text_analysis_widget.delete(1.0, tk.END)
    text_analysis_widget.insert(tk.END, extracted_text)

def on_right_click(event):
    highlighted_text = text_analysis_widget.selection_get()
    if highlighted_text:
        insight = get_insight(highlighted_text)
        messagebox.showinfo("Verse Insight", insight)

def display_page(chapter, page_num):
    image_path = os.path.join(BASE_DIR, chapter, f'page_{page_num}.jpg')
    if not os.path.exists(image_path):
        messagebox.showerror("Error", "The specified page does not exist.")
        return

    image = Image.open(image_path)
    photo = ImageTk.PhotoImage(image)

    canvas.image = photo
    canvas.config(scrollregion=canvas.bbox(tk.ALL))
    canvas.create_image(0, 0, anchor=tk.NW, image=photo)

    extracted_text = pytesseract.image_to_string(image, lang="hin")
    text_analysis_widget.delete(1.0, tk.END)
    text_analysis_widget.insert(tk.END, extracted_text)

def request_page():
    chapter = chapter_var.get()
    page_num = page_var.get()
    if chapter and page_num.isdigit():
        display_page(chapter, int(page_num))

root = tk.Tk()
root.title("Vedic Scriptures Analysis")
root.geometry("1200x700")

# Left frame for image
left_frame = tk.Frame(root)
left_frame.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)

upload_btn = tk.Button(left_frame, text="Upload Image", command=upload_image)
upload_btn.pack(pady=20)

chapter_var = tk.StringVar()
chapter_dropdown = tk.OptionMenu(left_frame, chapter_var, "Balkand", "Ayodhyakand", "Aranyakand", "Kishkindhakand", "Sundarkand", "Yudhkand", "Uttarkand")
chapter_dropdown.pack(pady=10)
chapter_var.set("Balkand")  # default value

page_var = tk.Entry(left_frame)
page_var.pack(pady=10)

fetch_btn = tk.Button(left_frame, text="Fetch Page", command=request_page)
fetch_btn.pack(pady=20)

canvas = tk.Canvas(left_frame, bg="white")
scroll_y = tk.Scrollbar(left_frame, orient="vertical", command=canvas.yview)
canvas.configure(yscrollcommand=scroll_y.set)
canvas.pack(fill="both", expand=True, padx=20, pady=20)
scroll_y.pack(side="right", fill="y")

# Right frame for text widgets
right_frame = tk.Frame(root)
right_frame.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True)

text_analysis_widget = tk.Text(right_frame, wrap=tk.WORD)
text_analysis_widget.pack(pady=20, padx=20, fill=tk.BOTH, expand=True)
text_analysis_widget.bind("<Button-3>", on_right_click)

general_question_widget = tk.Text(right_frame, wrap=tk.WORD, height=10)
general_question_widget.pack(pady=20, padx=20, fill=tk.BOTH)
general_question_widget.bind("<Return>", general_questions)

root.mainloop()
